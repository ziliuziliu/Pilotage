package com.eis.broker.daoimpl;

import com.eis.broker.dao.OrderLogDao;
import com.eis.broker.entity.OrderLog;
import com.eis.broker.repository.OrderLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class OrderLogDaoImpl implements OrderLogDao {

    private final OrderLogRepository orderLogRepository;

    @Autowired
    public OrderLogDaoImpl(OrderLogRepository orderLogRepository) {
        this.orderLogRepository = orderLogRepository;
    }

    @Override
    public OrderLog save(OrderLog orderLog) {
        Objects.requireNonNull(orderLog, "null orderLog --OrderLogDaoImpl save");
        return orderLogRepository.save(orderLog);
    }

}
