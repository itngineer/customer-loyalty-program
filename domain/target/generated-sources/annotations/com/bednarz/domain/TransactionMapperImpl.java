package com.bednarz.domain;

import com.bednarz.domain.model.Product;
import com.bednarz.domain.transaction.Transaction;
import com.bednarz.domain.transaction.Transaction.TransactionBuilder;
import com.bednarz.ports.model.OrderPort;
import com.bednarz.ports.model.ProductPort;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-09T04:51:36+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction orderInToTransaction(OrderPort orderPort) {
        if ( orderPort == null ) {
            return null;
        }

        TransactionBuilder transaction = Transaction.builder();

        transaction.customerId( orderPort.getCustomerId() );
        transaction.transactionDate( orderPort.getTransactionDate() );
        transaction.entries( productPortListToProductList( orderPort.getEntries() ) );
        transaction.points( orderPort.getPoints() );

        return transaction.build();
    }

    @Override
    public OrderPort transactionToOrderPort(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        List<ProductPort> entries = null;
        Long customerId = null;
        LocalDateTime transactionDate = null;
        Integer points = null;

        entries = productListToProductPortList( transaction.getEntries() );
        customerId = transaction.getCustomerId();
        transactionDate = transaction.getTransactionDate();
        points = transaction.getPoints();

        OrderPort orderPort = new OrderPort( customerId, entries, transactionDate, points );

        return orderPort;
    }

    protected Product productPortToProduct(ProductPort productPort) {
        if ( productPort == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productPort.getId() );
        product.setName( productPort.getName() );
        product.setPrice( productPort.getPrice() );

        return product;
    }

    protected List<Product> productPortListToProductList(List<ProductPort> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductPort productPort : list ) {
            list1.add( productPortToProduct( productPort ) );
        }

        return list1;
    }

    protected ProductPort productToProductPort(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductPort productPort = new ProductPort();

        productPort.setId( product.getId() );
        productPort.setName( product.getName() );
        productPort.setPrice( product.getPrice() );

        return productPort;
    }

    protected List<ProductPort> productListToProductPortList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductPort> list1 = new ArrayList<ProductPort>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductPort( product ) );
        }

        return list1;
    }
}
