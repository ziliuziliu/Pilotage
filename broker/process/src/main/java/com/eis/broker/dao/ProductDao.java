package com.eis.broker.dao;

import com.eis.broker.entity.ProductData;

import java.util.List;

public interface ProductDao {
    ProductData save(ProductData productData);
    List<ProductData> findAll();
}
