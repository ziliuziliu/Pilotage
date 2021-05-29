package com.eis.broker.repository;

import com.eis.broker.entity.TransactionData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionData, String> {
}
