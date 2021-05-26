package com.eis.broker.endpoint;

import com.eis.broker.message.OrderMsg;
import com.eis.broker.message.OrderStatusMsg;
import com.eis.broker.message.TransactionMsg;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private static Gson gson = new Gson();
    private static final String ORDER_TOPIC = "ORDER";

    public void sendMsg(TransactionMsg msg) {
        String json = gson.toJson(msg, msg.getClass());
        logger.info(json);
//        kafkaTemplate.send(msg.getBuyCompany() + "-transaction", json);
//        kafkaTemplate.send(msg.getSellCompany() + "-transaction", json);
    }

    public void sendMsg(OrderStatusMsg msg) {
        String json = gson.toJson(msg, msg.getClass());
        logger.info(json);
//        kafkaTemplate.send(msg.getTargetCompany() + "-status", json);
    }

    public void sendMsg(OrderMsg msg) {
        String json = gson.toJson(msg, msg.getClass());
        logger.info(json);
//        kafkaTemplate.send(ORDER_TOPIC, json);
    }
}
