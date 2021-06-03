package com.eis.broker.service;

import com.eis.broker.entity.TransactionData;

import java.util.List;

public interface TransactionService {
    List<TransactionData> findAll();
}
