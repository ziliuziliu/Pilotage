package com.eis.broker.service;

import com.eis.broker.message.MarketDepthMsg;
import com.eis.broker.orderbook.Order;
import com.eis.broker.orderbook.OrderBook;

public interface OrderBookService {
    void process(String product, String type, Order order);
    OrderBook initOrderBook(String product);
    MarketDepthMsg getMarketDepth(String product);
}
