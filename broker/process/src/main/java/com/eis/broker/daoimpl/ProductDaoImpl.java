package com.eis.broker.daoimpl;

import com.eis.broker.dao.ProductDao;
import com.eis.broker.entity.ProductData;
import com.eis.broker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDaoImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductData save(ProductData productData) {
        return productRepository.save(productData);
    }

}
