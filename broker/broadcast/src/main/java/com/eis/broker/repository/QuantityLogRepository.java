package com.eis.broker.repository;

import com.eis.broker.entity.QuantityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantityLogRepository extends JpaRepository<QuantityLog, Integer> {
    Page<QuantityLog> getByProduct(String product, Pageable pageable);
}
