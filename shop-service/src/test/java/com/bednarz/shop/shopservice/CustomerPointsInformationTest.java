package com.bednarz.shop.shopservice;

import com.bednarz.ports.model.CustomerInfo;
import com.bednarz.shop.infra.CustomerEntity;
import com.bednarz.shop.infra.TransactionEntity;
import com.bednarz.shop.infra.common.WrongDataException;
import com.bednarz.shop.infra.persistence.CustomerRepositoryAdapter;
import com.bednarz.shop.infra.persistence.JpaCustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CustomerPointsInformationTest {

    CustomerRepositoryAdapter customerRepositoryAdapter;
    CustomerEntity customerEntity = null;

    @BeforeEach
    void init(@Mock JpaCustomerRepository jpaCustomerRepository) {
        customerRepositoryAdapter = new CustomerRepositoryAdapter(jpaCustomerRepository);
        customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setName("jane");
        Mockito.lenient().when(jpaCustomerRepository.findById(any(Long.class))).thenReturn(
                Optional.of(customerEntity));
    }

    @Test
    public void shouldGetProperValueOfPointBasedOn100DaysDailyOrders() {
        int days = 100;
        Set<TransactionEntity> transactions = new HashSet<>();
        for (int i = 0; i < days; i++) {
            TransactionEntity tr = new TransactionEntity();
            tr.setId(i+1L);
            tr.setPoints(1);
            tr.setTransactionDate(LocalDateTime.now().minusDays(i));
            transactions.add(tr);
        }
        customerEntity.setTransactions(transactions);
        CustomerInfo customerInfo = customerRepositoryAdapter.getCustomerInfo(1L);
        long daysInLastMonth = Duration.between(LocalDateTime.now().minusMonths(1), LocalDateTime.now()).toDays();
        long daysInLast3Months = Duration.between(LocalDateTime.now().minusMonths(3), LocalDateTime.now()).toDays();

        Assertions.assertEquals(Long.valueOf(customerInfo.getLastMonthRewardPoints()), daysInLastMonth);
        Assertions.assertEquals(Long.valueOf(customerInfo.getLast3MonthRewardPoints()), daysInLast3Months);
    }

}
