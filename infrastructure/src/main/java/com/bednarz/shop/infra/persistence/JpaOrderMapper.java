package com.bednarz.shop.infra.persistence;

import com.bednarz.ports.model.OrderPort;
import com.bednarz.shop.infra.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProductEntityMapper.class })
public interface JpaOrderMapper {
    @Mapping(source = "customerId", target = "id")
    @Mapping(source = "entries", target = "products")
    @Mapping(source = "transactionDate", target = "transactionDate")
    TransactionEntity orderToTransaction(OrderPort orderDto);
}
