package com.eis.broker.repository;

import com.eis.broker.entity.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductData, Integer> {
}
