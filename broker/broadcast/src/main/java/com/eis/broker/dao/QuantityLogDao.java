package com.eis.broker.dao;

import com.eis.broker.entity.QuantityLog;

import java.util.List;

public interface QuantityLogDao {
    QuantityLog save(QuantityLog quantityLog);
    List<QuantityLog> getPriceLogs(String product, Integer hours);
}
