package com.bednarz.shop.infra.persistence;

import com.bednarz.ports.CustomerDataUseCase;
import com.bednarz.ports.CustomerRepositoryPort;
import com.bednarz.ports.model.CustomerInfo;
import com.bednarz.shop.infra.CustomerEntity;
import com.bednarz.shop.infra.TransactionEntity;
import com.bednarz.shop.infra.common.WrongDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Transactional
@Log
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final JpaCustomerRepository customerRepository;


    @Override
    public CustomerInfo getCustomerInfo(Long customerId) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(customerId);
        if (customerEntity.isEmpty()) {
            throw new WrongDataException("user with given id not exists");
        }

        Integer lastMonthPoints = getPointsFromLastXMonths(customerEntity.get(), 1);
        Integer last3MonthPoints = getPointsFromLastXMonths(customerEntity.get(), 3);

        return new CustomerInfo(
                customerEntity.get().getName(),
                lastMonthPoints,
                last3MonthPoints
        );

    }

    @Override
    public List<CustomerInfo> getCustomersInfo() {
        List<CustomerEntity> customers = customerRepository.findAll();
        List<CustomerInfo> customerInfos = new ArrayList<>();
        if (customers.isEmpty()) {
            throw new WrongDataException("No users exists in db");
        }
        for(CustomerEntity customer : customers){
            CustomerInfo customerInfo = new CustomerInfo(
                    customer.getName(),
                    getPointsFromLastXMonths(customer,1),
                    getPointsFromLastXMonths(customer,3)
            );
            customerInfos.add(customerInfo);
        }

        return customerInfos;
    }

    private Integer getPointsFromLastXMonths(CustomerEntity customer, int numOfMonths) {
        return customer.getTransactions().stream()
                .filter(tr -> tr.getTransactionDate().isAfter(LocalDateTime.now().minusMonths(numOfMonths)))
                .map(TransactionEntity::getPoints)
                .reduce(0, Integer::sum);
    }


}
