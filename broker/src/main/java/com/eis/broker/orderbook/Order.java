package com.eis.broker.orderbook;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
    private Integer orderId;
    private Integer quantity;
    private String trader;
    private String company;
    private String side;

    public Order() {
    }
}
