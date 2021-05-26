package com.eis.broker.websocket;

import com.eis.broker.message.MarketDepthMsg;
import com.eis.broker.orderbook.OrderBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(
        value ="/marketDepth/{trader}/{product}",
        encoders = { MarketDepthEncoder.class }
)
@Component
public class MarketDepthEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(MarketDepthEndpoint.class);

    private static ConcurrentHashMap<String, MarketDepthEndpoint> users = new ConcurrentHashMap<>();
    private Session session;
    private String trader;
    private String product;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void sendObject(MarketDepthMsg msg) {
        try {
            this.session.getBasicRemote().sendObject(msg);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    public void sendAll() {
        for (Map.Entry<String, MarketDepthEndpoint> entry : users.entrySet()) {
            String product = entry.getValue().product;
            OrderBook orderBook = (OrderBook) redisTemplate.opsForValue().get("orderbook-" + product);
            if (orderBook == null) logger.warn("----" + product + " orderbook is null");
            else entry.getValue().sendObject(new MarketDepthMsg(orderBook));
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("trader") String trader, @PathParam("product") String product) {
        logger.info(trader + " opens a websocket connection for " + product);
        this.session = session;
        this.trader = trader;
        this.product = product;
        users.remove(trader);
        users.put(trader, this);
    }

    @OnClose
    public void onClose() {
        logger.info(trader + " closes a websocket connection for " + product);
        users.remove(trader);
    }

    @OnMessage
    public void onMessage(String msg) {
    }

    @OnError
    public void onError(Throwable t) {
        logger.warn(t.getMessage());
    }

}
