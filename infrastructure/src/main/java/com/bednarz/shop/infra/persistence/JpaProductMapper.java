package com.bednarz.shop.infra.persistence;

import com.bednarz.ports.model.ProductPort;
import com.bednarz.shop.infra.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JpaProductMapper {
    @Mapping(source = "name", target = "productName")
    @Mapping(source = "price", target = "productPrice")
    @Mapping(target = "id", ignore = true)
    ProductEntity productPortToProductEntity(ProductPort productPort);


}
