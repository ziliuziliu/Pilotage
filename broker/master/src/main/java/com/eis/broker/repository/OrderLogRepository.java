package com.eis.broker.repository;

import com.eis.broker.entity.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLogRepository extends JpaRepository<OrderLog, String> {
}
