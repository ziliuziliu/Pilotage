package com.eis.broker.service;

import com.eis.broker.entity.Weight;

public interface AnalysisService {
    void addLoggingProduct(String product);
    void removeLoggingProduct(String product);
    Weight getWeight(String product, Integer hours);
}
