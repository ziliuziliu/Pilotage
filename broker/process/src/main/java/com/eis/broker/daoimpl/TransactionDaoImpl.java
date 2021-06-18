package com.eis.broker.daoimpl;

import com.eis.broker.dao.TransactionDao;
import com.eis.broker.entity.TransactionData;
import com.eis.broker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionDaoImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void save(TransactionData transactionData) {
        transactionRepository.save(transactionData);
    }

    @Override
    public List<TransactionData> findAll() {
        return transactionRepository.findAll();
    }
}
