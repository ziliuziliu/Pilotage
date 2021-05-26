package com.eis.broker.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderStatusMsg extends Msg {

    private String targetCompany;
    private String orderId;
    private String status;
    private String message;

    public OrderStatusMsg() {}
    public OrderStatusMsg(String targetCompany, String orderId, String status, String message) {
        this.targetCompany = targetCompany;
        this.orderId = orderId;
        this.status = status;
        this.message = message;
    }
}
