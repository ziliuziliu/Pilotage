package com.eis.broker.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransactionMsg extends Msg {

    private String tradeId;
    private String broker;
    private String product;
    private Integer price;
    private Integer quantity;
    private String sellName;
    private String sellCompany;
    private String buyName;
    private String buyCompany;
    private String initSide;

    public TransactionMsg() {}

}
