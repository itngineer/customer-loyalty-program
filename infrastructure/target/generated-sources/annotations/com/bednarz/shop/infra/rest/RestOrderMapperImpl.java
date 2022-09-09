package com.bednarz.shop.infra.rest;

import com.bednarz.ports.model.OrderPort;
import com.bednarz.ports.model.ProductPort;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-09T04:51:32+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class RestOrderMapperImpl implements RestOrderMapper {

    @Override
    public OrderPort toPorts(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        List<ProductPort> entries = null;
        Long customerId = null;
        LocalDateTime transactionDate = null;

        List<ProductPort> list = orderDto.getEntries();
        if ( list != null ) {
            entries = new ArrayList<ProductPort>( list );
        }
        customerId = orderDto.getCustomerId();
        transactionDate = orderDto.getTransactionDate();

        Integer points = null;

        OrderPort orderPort = new OrderPort( customerId, entries, transactionDate, points );

        return orderPort;
    }
}
