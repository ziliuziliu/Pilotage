package com.eis.broker.repository;

import com.eis.broker.entity.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductData, Integer> {
}
