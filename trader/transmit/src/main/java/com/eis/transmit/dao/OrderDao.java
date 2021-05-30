package com.eis.transmit.dao;

import com.eis.transmit.dto.OrderStatusInfo;
import com.eis.transmit.entity.Order;

import java.util.List;

public interface OrderDao {
    OrderStatusInfo saveOrder(Order order);

    OrderStatusInfo updateStatusByOrderId(OrderStatusInfo orderStatusInfo);

    List<Order> findAllByUserId(Integer userId);
}
