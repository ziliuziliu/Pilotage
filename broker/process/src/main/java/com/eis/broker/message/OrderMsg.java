package com.eis.broker.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderMsg extends Msg {

    private String orderId;
    private String product;
    private Integer quantity;
    private Integer price;
    private String side;
    private String type;
    private String company;
    private String trader;

    public OrderMsg() {}
    public OrderMsg(String orderId, String product, Integer quantity, String side,
                    String type, String company, String trader) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.side = side;
        this.type = type;
        this.company = company;
        this.trader = trader;
    }

}
