package com.bednarz.ports;

public interface OrderServiceFactory {

    PlaceOrderUseCase create(OrderPointCalculator pointCalculatorService, TransactionRepositoryPort transactionRepositoryPort);

}
