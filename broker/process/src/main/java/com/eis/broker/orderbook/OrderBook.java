package com.eis.broker.orderbook;

import com.eis.broker.message.Msg;
import com.eis.broker.message.OrderStatusMsg;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class OrderBook implements Serializable {

    private Integer productId;
    private String productName;
    private String broker;
    private Integer currentPrice;
    private Map<Integer, LinkedList<Order>> buyLimitOrder = new ConcurrentHashMap<>();
    private Map<Integer, LinkedList<Order>> buyStopOrder = new ConcurrentHashMap<>();
    private Map<Integer, LinkedList<Order>> sellLimitOrder = new ConcurrentHashMap<>();
    private Map<Integer, LinkedList<Order>> sellStopOrder = new ConcurrentHashMap<>();

    public OrderBook() {}

    private void addOrder(Order order, Map<Integer, LinkedList<Order>> map) {
        Integer price = order.getPrice();
        LinkedList<Order> orderList;
        if (!map.containsKey(price)) orderList = new LinkedList<>();
        else orderList = map.get(price);
        orderList.offer(order);
        map.put(price, orderList);
    }

    public Msg addToBuyLimitOrder(Order order) {
        Integer price = order.getPrice();
        if (price >= currentPrice) return null;
        addOrder(order, buyLimitOrder);
        return new OrderStatusMsg(order.getOrderId(), "WAITING", "Waiting to be executed.");
    }

    public Msg addToSellLimitOrder(Order order) {
        Integer price = order.getPrice();
        if (price < currentPrice) return null;
        addOrder(order, sellLimitOrder);
        return new OrderStatusMsg(order.getOrderId(), "WAITING", "Waiting to be executed.");
    }

    public Msg addToBuyStopOrder(Order order) {
        Integer price = order.getPrice();
        if (price <= currentPrice) return null;
        addOrder(order, buyStopOrder);
        return new OrderStatusMsg(order.getOrderId(), "WAITING", "Waiting to be executed.");
    }

    public Msg addToSellStopOrder(Order order) {
        Integer price = order.getPrice();
        if (price >= currentPrice) return null;
        addOrder(order, sellStopOrder);
        return new OrderStatusMsg(order.getOrderId(), "WAITING", "Waiting to be executed.");
    }

    public List<Msg> marketBuy(Order order) {
        return null;
    }

    public List<Msg> marketSell(Order order) {
        return null;
    }

    public Msg cancel(Order order) {
        return null;
    }

}
