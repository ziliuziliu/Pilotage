package com.eis.transmit.dao;

import com.eis.transmit.dto.OrderStatusInfo;
import com.eis.transmit.entity.Order;

public interface OrderDao {
    OrderStatusInfo saveOrder(Order order);

    OrderStatusInfo updateStatusByOrderId(OrderStatusInfo orderStatusInfo);
}
