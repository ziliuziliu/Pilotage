package com.eis.transmit.endpoint;

import com.eis.transmit.TransmitApplication;
import com.eis.transmit.dao.OrderDao;
import com.eis.transmit.dto.OrderStatusInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private final String TAG="KafkaConsumer ";

    @Autowired
    private OrderDao orderDao;

    @KafkaListener(topics = "STATUS")
    public void receive(String record){
        TransmitApplication.logger.info(TAG+"receive order blotter from kafka: "+record);
        OrderStatusInfo orderStatusInfo=TransmitApplication.gson.fromJson(record,OrderStatusInfo.class);
        orderDao.updateStatusByOrderId(orderStatusInfo);
    }
}
