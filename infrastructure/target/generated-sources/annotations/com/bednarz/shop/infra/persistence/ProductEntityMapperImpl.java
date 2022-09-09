package com.bednarz.shop.infra.persistence;

import com.bednarz.ports.model.ProductPort;
import com.bednarz.shop.infra.ProductEntity;
import com.bednarz.shop.infra.ProductEntity.ProductEntityBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-09T04:51:47+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class ProductEntityMapperImpl implements ProductEntityMapper {

    @Override
    public ProductEntity productPortToEntity(ProductPort productPort) {
        if ( productPort == null ) {
            return null;
        }

        ProductEntityBuilder productEntity = ProductEntity.builder();

        productEntity.productName( productPort.getName() );
        productEntity.productPrice( productPort.getPrice() );
        productEntity.id( productPort.getId() );

        return productEntity.build();
    }
}
