package org.eis.market.message;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class MarketDepthMessage extends Message{
    String product;
    String broker;
    Integer currentPrice;
    List<Integer> buyPrice;
    List<Integer> buyAmount;
    List<Integer> sellPrice;
    List<Integer> sellAmount;

    public MarketDepthMessage(@NonNull String product, @NonNull String broker, @NonNull Integer currentPrice,
                              @NonNull List<Integer> buyPrice, @NonNull List<Integer> buyAmount, @NonNull List<Integer> sellPrice,
                              @NonNull List<Integer> sellAmount) {
        this.product = product;
        this.broker = broker;
        this.currentPrice = currentPrice;
        this.buyPrice = buyPrice;
        this.buyAmount = buyAmount;
        this.sellPrice = sellPrice;
        this.sellAmount = sellAmount;
    }
}
