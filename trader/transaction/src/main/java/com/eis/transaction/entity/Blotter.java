package com.eis.transaction.entity;

import com.eis.common.util.UserSide;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="trader_blotter")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "bookId")
public class Blotter {
    @Id
    String tradeId;
    String broker;
    String product;
    Integer price;
    Integer quantity;
    String sellName;
    String sellCompany;
    String buyName;
    String buyCompany;
    String initSide;
}