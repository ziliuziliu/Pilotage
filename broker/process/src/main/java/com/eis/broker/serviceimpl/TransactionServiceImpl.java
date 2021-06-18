package com.eis.broker.serviceimpl;

import com.eis.broker.dao.TransactionDao;
import com.eis.broker.entity.TransactionData;
import com.eis.broker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public List<TransactionData> findAll() {
        return transactionDao.findAll();
    }

}
