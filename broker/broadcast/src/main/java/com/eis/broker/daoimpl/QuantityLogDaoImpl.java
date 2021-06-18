package com.eis.broker.daoimpl;

import com.eis.broker.dao.QuantityLogDao;
import com.eis.broker.entity.QuantityLog;
import com.eis.broker.repository.QuantityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuantityLogDaoImpl implements QuantityLogDao {

    private final QuantityLogRepository quantityLogRepository;

    @Autowired
    public QuantityLogDaoImpl(QuantityLogRepository quantityLogRepository) {
        this.quantityLogRepository = quantityLogRepository;
    }

    @Override
    public QuantityLog save(QuantityLog quantityLog) {
        return quantityLogRepository.save(quantityLog);
    }

    @Override
    public List<QuantityLog> getPriceLogs(String product, Integer hours) {
        Pageable pageable = PageRequest.of(0, hours*60, Sort.Direction.DESC, "createdTime");
        return quantityLogRepository.getByProduct(product, pageable).getContent();
    }
}
