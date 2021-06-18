package com.eis.broker.entity;

import com.eis.broker.message.TransactionMsg;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "transaction")
public class TransactionData {

    @Id
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

    public TransactionData() {}
    public TransactionData(TransactionMsg msg) {
        this.tradeId = msg.getTradeId();
        this.broker = msg.getBroker();
        this.product = msg.getProduct();
        this.price = msg.getPrice();
        this.quantity = msg.getQuantity();
        this.sellName = msg.getSellName();
        this.sellCompany = msg.getSellCompany();
        this.buyName = msg.getBuyName();
        this.buyCompany = msg.getBuyCompany();
        this.initSide = msg.getInitSide();
    }
}
