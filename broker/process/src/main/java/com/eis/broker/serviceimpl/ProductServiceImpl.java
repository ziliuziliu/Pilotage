package com.eis.broker.serviceimpl;

import com.eis.broker.dao.ProductDao;
import com.eis.broker.entity.ProductData;
import com.eis.broker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductData add(ProductData productData) {
        return productDao.save(productData);
    }

}
