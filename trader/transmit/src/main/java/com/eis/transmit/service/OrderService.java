package com.eis.transmit.service;

import com.eis.common.util.OrderType;
import com.eis.common.util.UserSide;
import com.eis.transmit.dto.OrderStatusInfo;
import com.eis.transmit.entity.Order;

import java.util.List;

public interface OrderService {
    OrderStatusInfo addOrder(String product, Integer quantity, Integer price,
                             UserSide side, OrderType type,Integer userId);

    OrderStatusInfo cancelOrder(String product, OrderType type, String orderId);

    List<Order> findAllByUserId(Integer userId);

    boolean batch(String product, Integer quantity, UserSide side, Integer userId);
}
