package com.eis.broker.entity;

import lombok.Data;

@Data
public class Weight {
    private Double[] weight;

    public Weight(Double[] weight) {
        this.weight = weight;
    }
}
