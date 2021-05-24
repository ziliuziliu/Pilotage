package com.eis.broker.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OrderLog {

    @Id
    private String logId;
    private String orderId;
    private String product;
    private Integer quantity;
    private Integer price;
    private String side;
    private String type;
    private String company;
    private String trader;

    @CreatedDate
    private Timestamp createdTime;

}
