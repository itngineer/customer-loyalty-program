package com.bednarz.shop.shopservice;

import com.bednarz.shop.infra.CustomerEntity;
import com.bednarz.shop.infra.persistence.JpaCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@SpringBootApplication(scanBasePackages = "com.bednarz")
@EnableJpaRepositories(basePackages = "com.bednarz")
@EntityScan("com.bednarz")
public class ShopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceApplication.class, args);
    }

    @Autowired
    JpaCustomerRepository customerRepository;

    @PostConstruct
    public void init() {
        if (customerRepository.findById(1L).isEmpty()) {
            var firstCustomer = new CustomerEntity();
            firstCustomer.setId(1L);
            firstCustomer.setName("Tony");
            firstCustomer.setTransactions(new ArrayList<>());
            customerRepository.save(firstCustomer);
        }
    }
}
