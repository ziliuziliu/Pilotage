package com.eis.broker.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "quantitylog")
public class QuantityLog {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer logId;
    private String product;
    private Integer quantity;

    @CreatedDate
    private Timestamp createdTime;

    public QuantityLog() {}
    public QuantityLog(String product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

}
