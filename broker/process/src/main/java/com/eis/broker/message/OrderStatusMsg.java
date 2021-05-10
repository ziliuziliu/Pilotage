package com.eis.broker.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderStatusMsg extends Msg {

    private String orderId;
    private String status;
    private String message;

    public OrderStatusMsg() {}
    public OrderStatusMsg(String orderId, String status, String message) {
        this.orderId = orderId;
        this.status = status;
        this.message = message;
    }
}
