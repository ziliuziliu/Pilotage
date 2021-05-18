package com.eis.broker.endpoint;

import com.eis.broker.dao.TransactionDao;
import com.eis.broker.entity.TransactionData;
import com.eis.broker.message.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static Gson gson = new Gson();
    private static final String MARKET_DEPTH_TOPIC = "MARKET_DEPTH";
    private static final String ORDER_TOPIC = "ORDER";

    public void sendMsg(TransactionMsg msg) {
        String json = gson.toJson(msg, msg.getClass());
        kafkaTemplate.send(msg.getBuyCompany(), json);
        kafkaTemplate.send(msg.getSellCompany(), json);
    }

    public void sendMsg(OrderStatusMsg msg) {
        String json = gson.toJson(msg, msg.getClass());
        kafkaTemplate.send(((OrderStatusMsg) msg).getTargetCompany(), json);
    }

    public void sendMsg(MarketDepthMsg msg) {
        String json = gson.toJson(msg, msg.getClass());
        kafkaTemplate.send(MARKET_DEPTH_TOPIC, json);
    }

    public void sendMsg(OrderMsg msg) {
        String json = gson.toJson(msg, msg.getClass());
        kafkaTemplate.send(ORDER_TOPIC, json);
    }
}
