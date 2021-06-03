package com.eis.broker.orderbook;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;
    private Integer price;
    private Integer quantity;
    private String trader;
    private String company;
    private String side;
    private String type;

    public Order() {}
    public Order(String orderId, Integer price, Integer quantity, String trader, String company, String side, String type) {
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.trader = trader;
        this.company = company;
        this.side = side;
        this.type = type;
    }
}
