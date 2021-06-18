package com.eis.broker.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
public class OrderLog {

    @Id
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
