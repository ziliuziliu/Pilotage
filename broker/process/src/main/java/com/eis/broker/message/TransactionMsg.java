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
    public TransactionMsg(String tradeId, String broker, String product, Integer price, Integer quantity,
                          String sellName, String sellCompany, String buyName, String buyCompany, String initSide) {
        this.tradeId = tradeId;
        this.broker = broker;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.sellName = sellName;
        this.sellCompany = sellCompany;
        this.buyName = buyName;
        this.buyCompany = buyCompany;
        this.initSide = initSide;
    }
}
