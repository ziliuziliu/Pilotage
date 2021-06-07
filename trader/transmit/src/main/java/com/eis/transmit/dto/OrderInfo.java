package com.eis.transmit.dto;

import com.eis.common.util.OrderStatus;
import com.eis.common.util.OrderType;
import com.eis.common.util.UserSide;
import lombok.Data;

@Data
public class OrderInfo {
    private String orderId;
    private String product;
    private Integer quantity;
    private Integer price;
    private OrderStatus status;
    private UserSide side;
    private OrderType type;
    private String company;
    private String trader;

    public OrderInfo(){}
    public OrderInfo(String orderId, String product, OrderType type) {
        this.orderId = orderId;
        this.product = product;
        this.type = type;
    }
    public OrderInfo(String orderId, String product, Integer quantity, Integer price,
                 UserSide side, OrderType type, String company,String trader) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.type = type;
        this.company=company;
        this.trader = trader;
    }
}
