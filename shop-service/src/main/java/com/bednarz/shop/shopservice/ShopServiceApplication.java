package com.bednarz.shop.shopservice;

import com.bednarz.shop.infra.CustomerEntity;
import com.bednarz.shop.infra.ProductEntity;
import com.bednarz.shop.infra.TransactionEntity;
import com.bednarz.shop.infra.persistence.JpaCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication(scanBasePackages = "com.bednarz")
@EnableJpaRepositories(basePackages = "com.bednarz")
@EntityScan("com.bednarz")
public class ShopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceApplication.class, args);
    }

    @Autowired
    JpaCustomerRepository customerRepository;
    @Autowired
    PointCalculator pointCalculator;

    @PostConstruct
    public void init() {

        TransactionEntity t1 = new TransactionEntity();
        t1.setTransactionDate(LocalDateTime.now().minusDays(40));
        Double transactionValue = 120D;
        t1.setTransactionValue(transactionValue);
        t1.setPoints(pointCalculator.transactionValueToPoints(transactionValue));

        if (customerRepository.findById(1L).isEmpty()) {
            var firstCustomer = new CustomerEntity();
            firstCustomer.setId(1L);
            firstCustomer.setName("Tony");
            firstCustomer.setTransactions(Set.of(t1));
            customerRepository.save(firstCustomer);
        }
        if (customerRepository.findById(2L).isEmpty()) {
            var firstCustomer = new CustomerEntity();
            firstCustomer.setId(2L);
            firstCustomer.setName("Lucene");
            firstCustomer.setTransactions(Set.of());
            customerRepository.save(firstCustomer);
        }
    }
}
