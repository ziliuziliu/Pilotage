package com.eis.transmit.entity;

import com.eis.common.util.OrderStatus;
import com.eis.common.util.OrderType;
import com.eis.common.util.UserSide;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trader_order")
@Data
public class Order {
    @Id
    private String orderId;
    private Integer userId;
    private String product;
    private Integer quantity;
    private Integer price;
    private String status;
    private String side;
    private String type;

    public Order(){}

    public Order(String orderId, Integer userId,String product, Integer quantity, Integer price,
                 String side, String type) {
        this.userId=userId;
        this.orderId = orderId;
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.type = type;
    }

}
