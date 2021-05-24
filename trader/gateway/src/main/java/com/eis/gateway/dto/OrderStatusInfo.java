package com.eis.gateway.dto;

import com.eis.common.util.OrderStatus;
import lombok.Data;

@Data
public class OrderStatusInfo {
    Integer orderId;
    OrderStatus status;

    public OrderStatusInfo(Integer orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }
}
