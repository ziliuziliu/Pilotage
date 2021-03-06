package com.eis.broker.orderbook;

import com.eis.broker.config.Constant;
import com.eis.broker.message.Msg;
import com.eis.broker.message.OrderMsg;
import com.eis.broker.message.OrderStatusMsg;
import com.eis.broker.message.TransactionMsg;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
public class OrderBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private String product;
    private String broker;
    private Integer currentPrice;
    private Map<Integer, LinkedList<Order>> buyLimitOrder = TreeMapBuilder.decreaseMap1();
    private Map<Integer, LinkedList<Order>> buyStopOrder = TreeMapBuilder.decreaseMap1();
    private Map<Integer, LinkedList<Order>> sellLimitOrder = TreeMapBuilder.increaseMap1();
    private Map<Integer, LinkedList<Order>> sellStopOrder = TreeMapBuilder.increaseMap1();
    private Map<Integer, Integer> buyVolume = TreeMapBuilder.decreaseMap2();
    private Map<Integer, Integer> sellVolume = TreeMapBuilder.increaseMap2();

    public OrderBook(String product) {
        this.product = product;
        this.broker = Constant.broker;

        Random random = new Random();
        this.currentPrice = random.nextInt(1000) + 1000;
        for (int i=0;i<10;i++) {
            Integer sellPrice = this.currentPrice + i * Constant.interval;
            LinkedList<Order> orders = new LinkedList<>();
            orders.push(new Order(UUID.randomUUID().toString().replaceAll("-", ""), sellPrice,
                    100, "ziliuziliu", "ziliuziliu Co.", "SELL", "LIMIT"));
            this.sellLimitOrder.put(sellPrice, orders);
            this.buyStopOrder.put(sellPrice, new LinkedList<>());
            this.sellVolume.put(sellPrice, 100);
        }
        for (int i=0;i<10;i++) {
            Integer buyPrice = this.currentPrice - (i+1) * Constant.interval;
            LinkedList<Order> orders = new LinkedList<>();
            orders.push(new Order(UUID.randomUUID().toString().replaceAll("-", ""), buyPrice,
                    100, "ziliuziliu", "ziliuziliu Co.", "BUY", "LIMIT"));
            this.buyLimitOrder.put(buyPrice, orders);
            this.sellStopOrder.put(buyPrice, new LinkedList<>());
            this.buyVolume.put(buyPrice, 100);
        }
    }

    private void addOrder(Order order, Map<Integer, LinkedList<Order>> map, Map<Integer, LinkedList<Order>> conjMap) {
        Integer price = order.getPrice();
        LinkedList<Order> orderList;
        if (!map.containsKey(price)) orderList = new LinkedList<>();
        else orderList = map.get(price);
        orderList.offer(order);
        map.put(price, orderList);
    }

    private void modifyVolume(Integer price, Integer volume, Map<Integer, Integer> map) {
        if (!map.containsKey(price)) map.put(price, volume);
        else map.put(price, map.get(price) + volume);
    }

    public Msg addToBuyLimitOrder(Order order) {
        Integer price = order.getPrice();
        if (price >= currentPrice) return null;
        addOrder(order, buyLimitOrder, buyStopOrder);
        modifyVolume(price, order.getQuantity(), buyVolume);
        return new OrderStatusMsg(order.getCompany(), order.getOrderId(), Constant.order_waiting, Constant.order_waiting_message);
    }

    public Msg addToSellLimitOrder(Order order) {
        Integer price = order.getPrice();
        if (price < currentPrice) return null;
        addOrder(order, sellLimitOrder, sellStopOrder);
        modifyVolume(price, order.getQuantity(), sellVolume);
        return new OrderStatusMsg(order.getCompany(), order.getOrderId(), Constant.order_waiting, Constant.order_waiting_message);
    }

    public Msg addToBuyStopOrder(Order order) {
        Integer price = order.getPrice();
        if (price <= currentPrice) return null;
        addOrder(order, buyStopOrder, buyLimitOrder);
        return new OrderStatusMsg(order.getCompany(), order.getOrderId(), Constant.order_waiting, Constant.order_waiting_message);
    }

    public Msg addToSellStopOrder(Order order) {
        Integer price = order.getPrice();
        if (price >= currentPrice) return null;
        addOrder(order, sellStopOrder, sellLimitOrder);
        return new OrderStatusMsg(order.getCompany(), order.getOrderId(), Constant.order_waiting, Constant.order_waiting_message);
    }

    public Integer searchAndDelete(String orderId, Map<Integer, LinkedList<Order>> map, Map<Integer, Integer> volumeMap) {
        Integer remainQuantity = 0;
        for (Map.Entry<Integer, LinkedList<Order>> entry : map.entrySet()) {
            Order existOrder = null;
            LinkedList<Order> orders = entry.getValue();
            for (Order order : orders) {
                if (order.getOrderId().equals(orderId)) {
                    existOrder = order;
                    break;
                }
            }
            if (existOrder != null) {
                orders.remove(existOrder);
                remainQuantity = existOrder.getQuantity();
                if (volumeMap != null) modifyVolume(entry.getKey(), -remainQuantity, volumeMap);
                break;
            }
        }
        return remainQuantity;
    }

    public Msg cancel(Order order) {
        String orderId = order.getOrderId();
        Integer remainQuantity;
        remainQuantity = searchAndDelete(orderId, buyLimitOrder, buyVolume);
        if (remainQuantity != 0) {
            refresh();
            return new OrderStatusMsg(order.getCompany(), orderId, "CANCELED", "Remain quantity: " + remainQuantity);
        }
        remainQuantity = searchAndDelete(orderId, sellLimitOrder, sellVolume);
        if (remainQuantity != 0) {
            refresh();
            return new OrderStatusMsg(order.getCompany(), orderId, "CANCELED", "Remain quantity: " + remainQuantity);
        }
        remainQuantity = searchAndDelete(orderId, buyStopOrder, null);
        if (remainQuantity != 0) {
            refresh();
            return new OrderStatusMsg(order.getCompany(), orderId, "CANCELED", "Remain quantity: " + remainQuantity);
        }
        remainQuantity = searchAndDelete(orderId, sellStopOrder, null);
        if (remainQuantity != 0) {
            refresh();
            return new OrderStatusMsg(order.getCompany(), orderId, "CANCELED", "Remain quantity: " + remainQuantity);
        }
        return new OrderStatusMsg(order.getCompany(), orderId, "ERROR", "No remain quantity.");
    }

    private void refresh() {
        List<Integer> buyRedundant = new ArrayList<>();
        for (Map.Entry<Integer, LinkedList<Order>> entry : buyLimitOrder.entrySet()) {
            if (entry.getValue().size() == 0) buyRedundant.add(entry.getKey());
            else break;
        }
        for (Integer redundant : buyRedundant) buyLimitOrder.remove(redundant);
        List<Integer> sellRedundant = new ArrayList<>();
        for (Map.Entry<Integer, LinkedList<Order>> entry : sellLimitOrder.entrySet()) {
            if (entry.getValue().size() == 0) sellRedundant.add(entry.getKey());
            else break;
        }
        for (Integer redundant : sellRedundant) sellLimitOrder.remove(redundant);
        for (Map.Entry<Integer, LinkedList<Order>> entry : sellLimitOrder.entrySet()) {
            currentPrice = entry.getKey();
            break;
        }
    }

    public List<Msg> marketBuy(Order order) {
        List<Msg> msgs = new ArrayList<>();
        Integer remainQuantity = order.getQuantity();
        for (Map.Entry<Integer, LinkedList<Order>> entry : sellLimitOrder.entrySet()) {
            Integer price = entry.getKey();
            LinkedList<Order> limitOrders = entry.getValue();
            for (Order limitOrder : limitOrders) {
                int dealQuantity = Math.min(limitOrder.getQuantity(), remainQuantity);
                msgs.add(new TransactionMsg(UUID.randomUUID().toString().replaceAll("-", ""),
                        Constant.broker, product, price, dealQuantity, limitOrder.getTrader(), limitOrder.getCompany(),
                        order.getTrader(), order.getCompany(), "BUY"));
                String executeMsg = (limitOrder.getQuantity().equals(dealQuantity)) ?
                        Constant.order_executed_message : Constant.order_partial_executed_message;
                msgs.add(new OrderStatusMsg(limitOrder.getCompany(), limitOrder.getOrderId(),
                        Constant.order_executed, executeMsg));
                remainQuantity -= dealQuantity;
                modifyVolume(price, -dealQuantity, sellVolume);
                limitOrder.setQuantity(limitOrder.getQuantity() - dealQuantity);
                if (remainQuantity == 0) break;
            }
            while (limitOrders.size() != 0 && limitOrders.getFirst().getQuantity() == 0)
                limitOrders.pop();
            if (limitOrders.size() == 0) {
                if (buyStopOrder.containsKey(price + Constant.interval)) {
                    for (Order stopOrder : buyStopOrder.get(price + Constant.interval))
                        msgs.add(new OrderMsg(stopOrder.getOrderId(), product, stopOrder.getQuantity(), "BUY", "MARKET",
                                stopOrder.getCompany(), stopOrder.getTrader()));
                    buyStopOrder.remove(price + Constant.interval);
                }
            }
            if (remainQuantity == 0) break;
        }
        msgs.add(new OrderStatusMsg(order.getCompany(), order.getOrderId(), Constant.order_executed, Constant.order_executed_message));
        refresh();
        return msgs;
    }

    public List<Msg> marketSell(Order order) {
        List<Msg> msgs = new ArrayList<>();
        Integer remainQuantity = order.getQuantity();
        for (Map.Entry<Integer, LinkedList<Order>> entry : buyLimitOrder.entrySet()) {
            Integer price = entry.getKey();
            LinkedList<Order> limitOrders = entry.getValue();
            for (Order limitOrder : limitOrders) {
                int dealQuantity = Math.min(limitOrder.getQuantity(), remainQuantity);
                msgs.add(new TransactionMsg(UUID.randomUUID().toString().replaceAll("-", ""),
                        Constant.broker, product, price, dealQuantity, order.getTrader(), order.getCompany(),
                        limitOrder.getTrader(), limitOrder.getCompany(),"SELL"));
                String executeMsg = (limitOrder.getQuantity().equals(dealQuantity)) ?
                        Constant.order_executed_message : Constant.order_partial_executed_message;
                msgs.add(new OrderStatusMsg(limitOrder.getCompany(), limitOrder.getOrderId(),
                        Constant.order_executed, executeMsg));
                remainQuantity -= dealQuantity;
                modifyVolume(price, -dealQuantity, buyVolume);
                limitOrder.setQuantity(limitOrder.getQuantity() - dealQuantity);
                if (remainQuantity == 0) break;
            }
            while (limitOrders.size() != 0 && limitOrders.getFirst().getQuantity() == 0)
                limitOrders.pop();
            if (limitOrders.size() == 0) {
                if (sellStopOrder.containsKey(price - Constant.interval)) {
                    for (Order stopOrder : sellStopOrder.get(price - Constant.interval))
                        msgs.add(new OrderMsg(stopOrder.getOrderId(), product, stopOrder.getQuantity(), "SELL", "MARKET",
                                stopOrder.getCompany(), stopOrder.getTrader()));
                    sellStopOrder.remove(price - Constant.interval);
                }
            }
            if (remainQuantity == 0) break;
        }
        msgs.add(new OrderStatusMsg(order.getCompany(), order.getOrderId(), Constant.order_executed, Constant.order_executed_message));
        refresh();
        return msgs;
    }

}
