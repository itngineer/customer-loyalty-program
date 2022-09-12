package com.bednarz.shop.infra.rest;

import com.bednarz.ports.model.OrderPort;
import com.bednarz.ports.model.TransactionPort;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestOrderMapper {
    OrderPort toOrderPorts(OrderDto orderDto);
}
