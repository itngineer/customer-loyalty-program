package com.bednarz.domain.service;

import com.bednarz.domain.TransactionMapper;
import com.bednarz.domain.transaction.Transaction;
import com.bednarz.ports.OrderPointCalculator;
import com.bednarz.ports.TransactionRepositoryPort;
import com.bednarz.ports.model.OrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.LocalDateTime;

@Log
@RequiredArgsConstructor
public class TransactionService {

    private final OrderPointCalculator orderPointCalculator;
    private final TransactionMapper mapper;
    private final TransactionRepositoryPort transactionRepositoryPort;

    public void perform(Transaction transaction){
        log.info("New transaction: " + transaction);
        Integer pointsGained = orderPointCalculator.calculate(mapper.transactionToOrderPort(transaction));
        log.info("User: " + transaction.getCustomerId() + " gained: " + pointsGained + " points");
        transaction.setPoints(pointsGained);
        if(transaction.getTransactionDate() == null){
            transaction.setTransactionDate(LocalDateTime.now());
        }
        OrderPort orderPort = mapper.transactionToOrderPort(transaction);
        transactionRepositoryPort.save(orderPort);
    }
    public void changeData(Transaction transaction){
        log.info("Change transaction data");
        OrderPort orderPort = mapper.transactionToOrderPort(transaction);
        if(!transaction.getEntries().isEmpty()){
            orderPort.setPoints(orderPointCalculator.calculate(orderPort));
        }
        transactionRepositoryPort.update(orderPort);

    }

    public void delete(Long transactionId) {
        transactionRepositoryPort.delete(transactionId);
    }
}
