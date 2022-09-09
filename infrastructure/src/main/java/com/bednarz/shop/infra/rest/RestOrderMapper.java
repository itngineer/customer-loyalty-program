package com.bednarz.shop.infra.rest;

import com.bednarz.ports.model.OrderPort;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestOrderMapper {
    OrderPort toPorts(OrderDto orderDto);
}
