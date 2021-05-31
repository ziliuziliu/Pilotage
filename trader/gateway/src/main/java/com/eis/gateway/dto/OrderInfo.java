package com.eis.gateway.dto;

import com.eis.common.util.OrderStatus;
import com.eis.common.util.OrderType;
import com.eis.common.util.UserSide;
import lombok.Data;

@Data
public class OrderInfo {
    private String orderId;
    private Integer userId;
    private String product;
    private Integer quantity;
    private Integer price;
    private OrderStatus status;
    private UserSide side;
    private OrderType type;
}
