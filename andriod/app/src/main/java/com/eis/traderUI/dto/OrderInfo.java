package com.eis.traderUI.dto;

import lombok.Data;

@Data
public class OrderInfo {
    private Integer userId;
    private String product;
    private Integer quantity;
    private Integer price;
    private String side;
    private String type;
    private String status;
}
