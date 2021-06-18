package com.eis.broker.message;

import com.eis.broker.config.Constant;
import com.eis.broker.orderbook.Order;
import com.eis.broker.orderbook.OrderBook;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class MarketDepthMsg extends Msg {

    private String product;
    private String broker;
    private Integer currentPrice;
    private Integer[] buyPrice = new Integer[5];
    private Integer[] buyVolume = new Integer[5];
    private Integer[] sellPrice = new Integer[5];
    private Integer[] sellVolume = new Integer[5];

    public MarketDepthMsg() {}
    public MarketDepthMsg(OrderBook orderBook) {
        this.product = orderBook.getProduct();
        this.broker = Constant.broker;
        this.currentPrice = orderBook.getCurrentPrice();
        int level = 0;
        for (Map.Entry<Integer, LinkedList<Order>> entry: orderBook.getBuyLimitOrder().entrySet()) {
            Integer price = entry.getKey();
            buyPrice[level] = price;
            buyVolume[level] = orderBook.getBuyVolume().get(price);
            level++;
            if (level == 5) break;
        }
        level = 0;
        for (Map.Entry<Integer, LinkedList<Order>> entry: orderBook.getSellLimitOrder().entrySet()) {
            Integer price = entry.getKey();
            sellPrice[level] = price;
            sellVolume[level] = orderBook.getSellVolume().get(price);
            level++;
            if (level == 5) break;
        }
    }
}
