package com.eis.broker.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MarketDepthMsg extends Msg {

    private String productName;
    private String broker;
    private Integer currentPrice;
    private Integer[] buyPrice;
    private Integer[] buyVolume;
    private Integer[] sellPrice;
    private Integer[] sellVolume;

}
