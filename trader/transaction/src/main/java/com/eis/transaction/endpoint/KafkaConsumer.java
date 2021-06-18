package com.eis.transaction.endpoint;

import com.eis.transaction.TransactionApplication;
import com.eis.transaction.dao.BlotterDao;
import com.eis.transaction.entity.Blotter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private final String TAG="KafkaConsumer ";

    @Autowired
    private BlotterDao blotterDao;

    @KafkaListener(topics="TRANSACTION")
    public void saveBlotter(String record) {
        TransactionApplication.logger.info(TAG+"receive order blotter from kafka: "+record);
        Blotter blotter=TransactionApplication.gson.fromJson(record,Blotter.class);
        blotterDao.saveBlotter(blotter);
    }
}
