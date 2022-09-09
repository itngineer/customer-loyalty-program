package com.bednarz.shop.infra.persistence;

import com.bednarz.shop.infra.TransactionEntity;
import com.bednarz.ports.TransactionRepositoryPort;
import com.bednarz.ports.model.OrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final JpaTransactionRepository transactionRepository;
    private final JpaCustomerRepository customerRepository;
    private final JpaOrderMapper orderMapper;


    @Override
    public void save(OrderPort order) {
        customerRepository.findById(order.getCustomerId());
        TransactionEntity transactionEntity = orderMapper.orderToTransaction(order);
        transactionRepository.save(transactionEntity);
        System.out.printf("ILOSC: " + transactionRepository.findAll().size());
    }
}
