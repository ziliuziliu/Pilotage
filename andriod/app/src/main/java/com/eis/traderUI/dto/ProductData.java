package com.eis.traderUI.dto;

import lombok.Data;

@Data
public class ProductData {
    private Integer productId;
    private String productName;
    private String productInfo;

    public ProductData(String productName, String productInfo) {
        this.productName = productName;
        this.productInfo = productInfo;
    }
}
