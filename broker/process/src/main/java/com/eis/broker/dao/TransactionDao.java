package com.eis.broker.dao;

import com.eis.broker.entity.TransactionData;

import java.util.List;

public interface TransactionDao {
    void save(TransactionData transactionData);
    List<TransactionData> findAll();
}
