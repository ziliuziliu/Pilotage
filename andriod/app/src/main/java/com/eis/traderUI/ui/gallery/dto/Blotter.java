package com.eis.traderUI.ui.gallery.dto;

import lombok.Data;

@Data
public class Blotter {
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
}
