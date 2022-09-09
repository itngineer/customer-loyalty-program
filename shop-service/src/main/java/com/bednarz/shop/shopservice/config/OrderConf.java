package com.bednarz.shop.shopservice.config;

import com.bednarz.domain.DefaultServiceFactory;
import com.bednarz.ports.CustomerDataUseCase;
import com.bednarz.ports.CustomerRepositoryPort;
import com.bednarz.ports.OrderPointCalculator;
import com.bednarz.ports.OrderServiceFactory;
import com.bednarz.ports.PlaceOrderUseCase;
import com.bednarz.ports.TransactionRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConf {


   private static final OrderServiceFactory orderServiceFactory = new DefaultServiceFactory();

    @Bean
    public PlaceOrderUseCase placeOrderUseCase(OrderPointCalculator pointCalculatorService,
                                               TransactionRepositoryPort transactionRepositoryPort) {
        return orderServiceFactory.create(pointCalculatorService, transactionRepositoryPort);
    }
    @Bean
    public CustomerDataUseCase customerDataUseCase(CustomerRepositoryPort customerRepositoryPort) {
        return orderServiceFactory.create(customerRepositoryPort);
    }
}
