package com.bednarz.shop.infra.persistence;

import com.bednarz.shop.infra.CustomerEntity;
import com.bednarz.shop.infra.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
