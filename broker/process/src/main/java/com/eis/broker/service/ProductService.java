package com.eis.broker.service;

import com.eis.broker.entity.ProductData;

import java.util.List;

public interface ProductService {
    ProductData add(ProductData productData);
    List<ProductData> findAll();
}
