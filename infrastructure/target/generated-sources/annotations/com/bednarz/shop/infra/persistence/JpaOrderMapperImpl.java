package com.bednarz.shop.infra.persistence;

import com.bednarz.ports.model.OrderPort;
import com.bednarz.ports.model.ProductPort;
import com.bednarz.shop.infra.ProductEntity;
import com.bednarz.shop.infra.TransactionEntity;
import com.bednarz.shop.infra.TransactionEntity.TransactionEntityBuilder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-09T04:51:32+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class JpaOrderMapperImpl implements JpaOrderMapper {

    @Autowired
    private ProductEntityMapper productEntityMapper;

    @Override
    public TransactionEntity orderToTransaction(OrderPort orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        TransactionEntityBuilder transactionEntity = TransactionEntity.builder();

        transactionEntity.id( orderDto.getCustomerId() );
        transactionEntity.products( productPortListToProductEntitySet( orderDto.getEntries() ) );
        transactionEntity.transactionDate( orderDto.getTransactionDate() );
        transactionEntity.points( orderDto.getPoints() );

        return transactionEntity.build();
    }

    protected Set<ProductEntity> productPortListToProductEntitySet(List<ProductPort> list) {
        if ( list == null ) {
            return null;
        }

        Set<ProductEntity> set = new HashSet<ProductEntity>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ProductPort productPort : list ) {
            set.add( productEntityMapper.productPortToEntity( productPort ) );
        }

        return set;
    }
}
