package com.eis.broker.entity;

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

    public TransactionData() {
    }

}
