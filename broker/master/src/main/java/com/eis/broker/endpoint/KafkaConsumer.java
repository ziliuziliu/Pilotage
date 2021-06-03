package com.eis.broker.endpoint;

import com.eis.broker.entity.OrderLog;
import com.eis.broker.entity.OrderQueue;
import com.eis.broker.service.OrderLogService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final OrderQueue orderQueue;
    private final OrderLogService orderLogService;
    private static Gson gson = new Gson();

    @Autowired
    public KafkaConsumer(OrderQueue orderQueue, OrderLogService orderLogService) {
        this.orderQueue = orderQueue;
        this.orderLogService = orderLogService;
    }

//    @KafkaListener(topics = "ORDER")
//    public void receive(String msg) {
//        OrderLog orderLog = gson.fromJson(msg, OrderLog.class);
//        orderLogService.saveOrderLog(orderLog);
//        orderQueue.addToQueue(orderLog);
//    }

}
