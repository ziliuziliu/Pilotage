package com.eis.broker.entity;

import com.eis.broker.endpoint.DispatchGrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class OrderQueue {

    private ConcurrentHashMap<String, LinkedList<OrderLog>> queues = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Boolean> status = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(OrderQueue.class);

    private final DispatchGrpcClient dispatchGrpcClient;

    @Autowired
    public OrderQueue(DispatchGrpcClient dispatchGrpcClient) {
        this.dispatchGrpcClient = dispatchGrpcClient;
    }

    class DispatchThread extends Thread {

        @Override
        @SuppressWarnings("InfiniteLoopStatement")
        public void run() {
            while (true) {
                try {
                    sendOrderRR();
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @PostConstruct
    public void initDispatchThread() {
        DispatchThread dispatchThread = new DispatchThread();
        dispatchThread.start();
    }

    public void addToQueue(OrderLog orderLog) {
        String product = orderLog.getProduct();
        if (!queues.containsKey(product)) {
            queues.put(product, new LinkedList<>());
            locks.put(product, new ReentrantLock());
            status.put(product, true);
        }
        ReentrantLock lock = locks.get(product);
        lock.lock();
        LinkedList<OrderLog> queue = queues.get(product);
        queue.add(orderLog);
        queues.put(product, queue);
        lock.unlock();
    }

    public void sendOrderRR() {
        for (Map.Entry<String, LinkedList<OrderLog>> entry : queues.entrySet()) {
            String product = entry.getKey();
            ReentrantLock lock = locks.get(product);
            lock.lock();
            if (!status.get(product)) {
                lock.unlock();
                return;
            }
            LinkedList<OrderLog> queue = queues.get(product);
            if (queue.size() == 0) {
                lock.unlock();
                return;
            }
            status.put(product, false);
            OrderLog orderLog = queue.pop();
            logger.info("----Dispatching order " + orderLog.getOrderId() + "----");
            boolean response = dispatchGrpcClient.dispatch(orderLog);
            logger.info(String.valueOf(response));
            lock.unlock();
        }
    }

    public void productAvailable(String product) {
        status.put(product, true);
    }
}
