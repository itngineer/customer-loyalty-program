package com.bednarz.domain;

import com.bednarz.domain.service.TransactionService;
import com.bednarz.ports.OrderPointCalculator;
import com.bednarz.ports.OrderServiceFactory;
import com.bednarz.ports.PlaceOrderUseCase;
import com.bednarz.ports.TransactionRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

@RequiredArgsConstructor
public class DefaultOrderServiceFactory implements OrderServiceFactory {

    private final TransactionMapper mapper = Mappers.getMapper(TransactionMapper.class);;

    public PlaceOrderUseCase create(OrderPointCalculator pointCalculatorService, TransactionRepositoryPort transactionRepositoryPort) {
        var transactionService = new TransactionService(pointCalculatorService, mapper, transactionRepositoryPort);
        return new TransactionAdapter(transactionService, mapper);
    }

}
