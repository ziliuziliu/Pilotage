package org.eis.market.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class JoinMessage extends Message{
    private String company;
    private String trader;
    private String product;

    public JoinMessage(String company, String trader, String product) {
        this.company = company;
        this.trader = trader;
        this.product = product;
    }
}
