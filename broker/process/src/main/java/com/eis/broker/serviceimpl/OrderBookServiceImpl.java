package com.eis.broker.serviceimpl;

import com.eis.broker.endpoint.KafkaProducer;
import com.eis.broker.entity.OrderLog;
import com.eis.broker.message.Msg;
import com.eis.broker.orderbook.Order;
import com.eis.broker.orderbook.OrderBook;
import com.eis.broker.service.OrderBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBookServiceImpl implements OrderBookService {

    private static final Logger logger = LoggerFactory.getLogger(OrderBookServiceImpl.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void process(String product, String type, Order order) {

        List<Msg> msgs = new ArrayList<>();
        Msg msg;

        logger.info("----getting " + product + " orderbook from redis");
        OrderBook orderBook = (OrderBook) redisTemplate.opsForValue().get("orderbook-" + product);
        if (orderBook == null) {
            logger.warn("----" + product + " orderbook is null");
            redisTemplate.opsForValue().set(product, "AVAILABLE");
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
        kafkaProducer.sendMsgs(msgs);

        logger.info("----writing " + product + " orderbook back----");
        redisTemplate.opsForValue().set("orderbook-" + product, orderBook);
    }
}
