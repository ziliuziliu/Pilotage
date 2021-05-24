package com.eis.transaction.serviceImpl;

import com.eis.transaction.TransactionApplication;
import com.eis.transaction.dao.BlotterDao;
import com.eis.transaction.entity.Blotter;
import com.eis.transaction.service.BlotterService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

public class BlotterServiceImpl implements BlotterService {
    private final String TAG="BlotterService";

    @Autowired
    private BlotterDao blotterDao;

    @KafkaListener(topics={})
    public void saveBlotter(ConsumerRecord<?,?> record) {
        TransactionApplication.logger.info(TAG+"receive order blotter from kafka: "+record.value().toString());
        Blotter blotter=TransactionApplication.gson.fromJson(record.value().toString(),Blotter.class);
        blotterDao.saveBlotter(blotter);
    }

    @Override
    public List<Blotter> getOrderBlotter(String company, String trader) {
        return null;
    }
}
