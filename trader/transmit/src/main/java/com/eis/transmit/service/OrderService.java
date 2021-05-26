package com.eis.transmit.service;

import com.eis.common.util.OrderType;
import com.eis.common.util.UserSide;
import com.eis.transmit.dto.OrderStatusInfo;

public interface OrderService {
    OrderStatusInfo addOrder(String product, Integer quantity, Integer price,
                             UserSide side, OrderType type,String company,String trader);
}
