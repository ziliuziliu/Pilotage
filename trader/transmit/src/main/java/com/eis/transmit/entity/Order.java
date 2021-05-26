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
@Table(name="order")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "orderId")
@Data
public class Order {
    @Id
    private String orderId;
    private String product;
    private Integer quantity;
    private Integer price;
    private OrderStatus status;
    private UserSide side;
    private OrderType type;
    private String company;
    private String trader;
    private Long id;

    public Order(){}

    public Order(String orderId, String product, Integer quantity, Integer price,
                 UserSide side, OrderType type, String trader,String company) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.type = type;
        this.trader = trader;
        this.company=company;
    }
}
