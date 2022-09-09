package com.bednarz.shop.infra.persistence;

import com.bednarz.shop.infra.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
