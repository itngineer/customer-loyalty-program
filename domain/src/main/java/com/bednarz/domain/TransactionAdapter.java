package com.bednarz.domain;

import com.bednarz.domain.service.TransactionService;
import com.bednarz.domain.transaction.Transaction;
import com.bednarz.ports.PlaceOrderUseCase;
import com.bednarz.ports.model.OrderPort;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransactionAdapter implements PlaceOrderUseCase {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @Override
    public void place(OrderPort orderPort) {
        Transaction transaction = transactionMapper.orderInToTransaction(orderPort);
        transactionService.perform(transaction);
    }
}
