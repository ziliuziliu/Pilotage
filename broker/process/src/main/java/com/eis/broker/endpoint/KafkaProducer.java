package com.eis.broker.endpoint;

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
    private static final String STATUS_TOPIC = "STATUS";
    private static final String TRANSACTION_TOPIC = "TRANSACTION";
    private static final String MARKET_DEPTH_TOPIC = "MARKET_DEPTH";
    private static final String ORDER_TOPIC = "ORDER";

    public void sendMsgs(List<Msg> msgs) {
        for (Msg msg : msgs)
            sendMsg(msg);
    }

    public void sendMsg(Msg msg) {
        String json = gson.toJson(msg, msg.getClass());
        if (msg instanceof TransactionMsg)
            kafkaTemplate.send(TRANSACTION_TOPIC, json);
        else if (msg instanceof OrderStatusMsg)
            kafkaTemplate.send(STATUS_TOPIC, json);
        else if (msg instanceof MarketDepthMsg)
            kafkaTemplate.send(MARKET_DEPTH_TOPIC, json);
        else if (msg instanceof OrderMsg)
            kafkaTemplate.send(ORDER_TOPIC, json);
    }
}
