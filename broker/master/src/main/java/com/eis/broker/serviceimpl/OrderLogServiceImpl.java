package com.eis.broker.serviceimpl;

import com.eis.broker.dao.OrderLogDao;
import com.eis.broker.entity.OrderLog;
import com.eis.broker.service.OrderLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderLogServiceImpl implements OrderLogService {

    private static final Logger logger = LoggerFactory.getLogger(OrderLogServiceImpl.class);
    private final OrderLogDao orderLogDao;

    @Autowired
    public OrderLogServiceImpl(OrderLogDao orderLogDao) {
        this.orderLogDao = orderLogDao;
    }

    @Override
    public void saveOrderLog(OrderLog orderLog) {
        logger.info("----writing log for order " + orderLog.getOrderId() + "----");
        orderLog.setLogId(UUID.randomUUID().toString().replaceAll("-", ""));
        orderLogDao.save(orderLog);
        logger.info("----writing log for order " + orderLog.getOrderId() + " completed----");
    }

}
