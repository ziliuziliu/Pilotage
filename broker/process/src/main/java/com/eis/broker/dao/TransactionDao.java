package com.eis.broker.dao;

import com.eis.broker.entity.TransactionData;

public interface TransactionDao {
    void save(TransactionData transactionData);
}
