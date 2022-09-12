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

        ProductEntity p1 = new ProductEntity();
        p1.setProductName("Notebook");
        p1.setProductPrice(90D);

        ProductEntity p2 = new ProductEntity();
        p2.setProductName("Keyboard");
        p2.setProductPrice(30D);

        Set<ProductEntity> products = Set.of(p1, p2);
        TransactionEntity t1 = new TransactionEntity();
        t1.setTransactionDate(LocalDateTime.now().minusDays(40));
        t1.setProducts(products);
        Double transactionValue = products.stream().map(ProductEntity::getProductPrice)
                .reduce(0D, Double::sum);
        t1.setTransactionValue(transactionValue);
        t1.setPoints(pointCalculator.transactionValueToPoints(transactionValue));

        if (customerRepository.findById(1L).isEmpty()) {
            var firstCustomer = new CustomerEntity();
            firstCustomer.setId(1L);
            firstCustomer.setName("Tony");
            firstCustomer.setTransactions(Set.of(t1));
            t1.setCustomer(firstCustomer);
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
