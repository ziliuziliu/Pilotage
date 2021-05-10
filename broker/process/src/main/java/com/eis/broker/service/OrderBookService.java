package com.eis.broker.service;

import com.eis.broker.orderbook.Order;

public interface OrderBookService {
    void process(String product, String type, Order order);
}
