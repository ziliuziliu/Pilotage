package com.eis.broker.orderbook;

import com.eis.broker.entity.OrderLog;
import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {

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
    public Order(OrderLog orderLog) {
        this.orderId = orderLog.getOrderId();
        this.price = orderLog.getPrice();
        this.quantity = orderLog.getQuantity();
        this.trader = orderLog.getTrader();
        this.company = orderLog.getCompany();
        this.side = orderLog.getSide();
    }
}
