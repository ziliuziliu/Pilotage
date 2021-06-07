package com.eis.traderUI.dto;

import java.util.List;

import lombok.Data;

@Data
public class MarketDepth {
    private String product;
    private String broker;
    private Integer currentPrice;
    private List<Integer> buyPrice;
    private List<Integer> buyVolume;
    private List<Integer> sellPrice;
    private List<Integer> sellVolume;
}
