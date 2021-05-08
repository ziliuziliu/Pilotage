package com.eis.broker.orderbook;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

@Data
public class OrderBook implements Serializable {
    private Integer productId;
    private String productName;
    private Integer currentPrice;
    private Map<Integer, LinkedList<Order>> limitOrder;
    private Map<Integer, LinkedList<Order>> stopOrder;

    public OrderBook() {
    }
}
