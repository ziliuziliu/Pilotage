package com.eis.broker.serviceimpl;

import com.eis.broker.dao.TransactionDao;
import com.eis.broker.endpoint.DispatchGrpcClient;
import com.eis.broker.endpoint.KafkaProducer;
import com.eis.broker.entity.TransactionData;
import com.eis.broker.message.*;
import com.eis.broker.orderbook.Order;
import com.eis.broker.orderbook.OrderBook;
import com.eis.broker.service.MasterFeignClient;
import com.eis.broker.service.OrderBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBookServiceImpl implements OrderBookService {

    private static final Logger logger = LoggerFactory.getLogger(OrderBookServiceImpl.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private DispatchGrpcClient dispatchGrpcClient;

    @Autowired
    private MasterFeignClient masterFeignClient;

    @Override
    public OrderBook initOrderBook(String product) {
        OrderBook orderBook = new OrderBook(product);
        redisTemplate.opsForValue().set("orderbook-" + product, orderBook);
        stringRedisTemplate.opsForValue().set("quantity-" + product, "0");
        return orderBook;
    }

    @Override
    public MarketDepthMsg getMarketDepth(String product) {
        OrderBook orderBook = (OrderBook) redisTemplate.opsForValue().get("orderbook-" + product);
        if (orderBook == null) {
            logger.warn("----" + product + " orderbook is null");
            return new MarketDepthMsg();
        }
        return new MarketDepthMsg(orderBook);
    }

    @Async("orderThreadExecutor")
    @Override
    public void process(String product, String type, Order order) {

        List<Msg> msgs = new ArrayList<>();
        Msg msg;

        logger.info("----getting " + product + " orderbook from redis");
        OrderBook orderBook = (OrderBook) redisTemplate.opsForValue().get("orderbook-" + product);
        if (orderBook == null) {
            logger.warn("----" + product + " orderbook is null");
            //notify master
            return;
        }

        logger.info("----processing order " + order.getOrderId() + "----");
        switch (type) {
            case "MARKET":
                msgs.addAll(order.getSide().equals("BUY") ? orderBook.marketBuy(order) : orderBook.marketSell(order));
                break;
            case "LIMIT":
                msg = order.getSide().equals("BUY") ?
                        orderBook.addToBuyLimitOrder(order) : orderBook.addToSellLimitOrder(order);
                if (msg == null)
                    msgs.addAll(order.getSide().equals("BUY") ? orderBook.marketBuy(order) : orderBook.marketSell(order));
                else msgs.add(msg);
                break;
            case "STOP":
                msg = order.getSide().equals("BUY") ?
                        orderBook.addToBuyStopOrder(order) : orderBook.addToSellStopOrder(order);
                if (msg == null)
                    msgs.addAll(order.getSide().equals("BUY") ? orderBook.marketBuy(order) : orderBook.marketSell(order));
                else msgs.add(msg);
                break;
            case "CANCEL":
                msgs.add(orderBook.cancel(order));
                break;
            default:
                break;
        }

        logger.info("----broadcasting----");
        msgs.forEach(m -> {
            if (m instanceof TransactionMsg) {
                String quantity = stringRedisTemplate.opsForValue().get("quantity-" + product);
                if (quantity != null) {
                    int newQ = Integer.parseInt(quantity) + ((TransactionMsg) m).getQuantity();
                    stringRedisTemplate.opsForValue().set("quantity-" + product, Integer.toString(newQ));
                }
                transactionDao.save(new TransactionData((TransactionMsg) m));
                kafkaProducer.sendMsg((TransactionMsg) m);
            }
            else if (m instanceof OrderStatusMsg)
                kafkaProducer.sendMsg((OrderStatusMsg) m);
            else if (m instanceof OrderMsg)
                kafkaProducer.sendMsg((OrderMsg) m);
        });

        logger.info("----writing " + product + " orderbook back----");
        redisTemplate.opsForValue().set("orderbook-" + product, orderBook);

        logger.info("----notifying master----");
//        boolean response = dispatchGrpcClient.finished(product);
        boolean response = masterFeignClient.available(product);
        logger.info(String.valueOf(response));
    }
}
