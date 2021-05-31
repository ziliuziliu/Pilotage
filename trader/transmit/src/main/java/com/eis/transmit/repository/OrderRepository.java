package com.eis.transmit.repository;

import com.eis.transmit.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    @Transactional
    @Modifying
    @Query(value="update Order set status=:status where orderId= :orderId")
    void updateStatusByOrderId(@Param("status") String status,@Param("orderId") String orderId);

    List<Order> findAllByUserId(Integer userId);
}
