package com.eis.transaction.serviceImpl;

import com.eis.transaction.TransactionApplication;
import com.eis.transaction.dao.BlotterDao;
import com.eis.transaction.dto.UserInfo;
import com.eis.transaction.entity.Blotter;
import com.eis.transaction.service.BlotterService;
import com.eis.transaction.service.UserFeignService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlotterServiceImpl implements BlotterService {
    private final String TAG="BlotterService";

    @Autowired
    private BlotterDao blotterDao;

    @Autowired
    private UserFeignService userFeignService;

    @Override
    public List<Blotter> findByUserId(Integer userId) {
        UserInfo userInfo =userFeignService.findByUserId(userId).getData();
        if(userInfo==null){
            TransactionApplication.logger.info(TAG+"null user");
            return null;
        }
        List<Blotter> buyResult=blotterDao.findAllByBuyNameAndBuyCompany(userInfo.getUsername(),userInfo.getCompany());
        List<Blotter> sellResult=blotterDao.findAllBySellNameAndSellCompany(userInfo.getUsername(),userInfo.getCompany());
        buyResult.addAll(sellResult);
        return buyResult;
    }
}
