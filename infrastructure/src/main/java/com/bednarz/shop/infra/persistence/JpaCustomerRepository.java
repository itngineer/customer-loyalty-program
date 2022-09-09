package com.bednarz.shop.infra.persistence;

import com.bednarz.shop.infra.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
