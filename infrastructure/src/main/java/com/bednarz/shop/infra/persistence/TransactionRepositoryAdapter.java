package com.bednarz.shop.infra.persistence;

import com.bednarz.ports.model.ProductPort;
import com.bednarz.shop.infra.CustomerEntity;
import com.bednarz.shop.infra.TransactionEntity;
import com.bednarz.ports.TransactionRepositoryPort;
import com.bednarz.ports.model.OrderPort;
import com.bednarz.shop.infra.common.WrongDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Transactional
@Log
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final JpaTransactionRepository transactionRepository;
    private final JpaCustomerRepository customerRepository;
    private final JpaOrderMapper orderMapper;


    @Override
    public void save(OrderPort order) {
        Optional<CustomerEntity> byId = customerRepository.findById(order.getCustomerId());
        if (byId.isEmpty()) {
            throw new WrongDataException("user with given id not exists");
        }

        CustomerEntity customer = byId.get();
        TransactionEntity transactionEntity = orderMapper.orderToTransaction(order);
        Double transactionValue = order.getEntries().stream()
                .map(ProductPort::getPrice).reduce(0D, Double::sum);
        transactionEntity.setTransactionValue(transactionValue);
        transactionEntity.setCustomer(customer);
        customer.getTransactions().add(transactionEntity);
        customerRepository.save(customer);
        log.info("Points: " + transactionRepository.findAll().size());
    }
}
