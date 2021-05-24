package com.eis.broker.endpoint;

import com.eis.broker.message.OrderMsg;
import com.eis.broker.message.OrderStatusMsg;
import com.eis.broker.message.TransactionMsg;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static Gson gson = new Gson();
    private static final String ORDER_TOPIC = "ORDER";

    public void sendMsg(TransactionMsg msg) {
        String json = gson.toJson(msg, msg.getClass());
        System.out.println(json);
//        kafkaTemplate.send(msg.getBuyCompany(), json);
//        kafkaTemplate.send(msg.getSellCompany(), json);
    }

    public void sendMsg(OrderStatusMsg msg) {
        String json = gson.toJson(msg, msg.getClass());
        System.out.println(json);
//        kafkaTemplate.send(msg.getTargetCompany(), json);
    }

    public void sendMsg(OrderMsg msg) {
        String json = gson.toJson(msg, msg.getClass());
        System.out.println(json);
//        kafkaTemplate.send(ORDER_TOPIC, json);
    }
}
