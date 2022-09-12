package com.bednarz.shop.shopservice;

import com.bednarz.ports.model.OrderPort;
import com.bednarz.ports.model.ProductPort;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointCalculatorTest {

    PointCalculator pointCalculator = new PointCalculator();

    @Test
    public void calculatorAlgorithmTest() {
        ProductPort pp = new ProductPort();
        pp.setId(1L);
        pp.setName("Notebook");
        pp.setPrice(120D);
        OrderPort orderPort = new OrderPort(1L, null, List.of(pp), LocalDateTime.of(2001, 2, 3, 4, 5), 0);
        Integer points = pointCalculator.calculate(orderPort);
        assertTrue(points == 90);
    }

    @Test
    public void calculatorDoublePriceTest() {
        ProductPort p1 = new ProductPort();
        p1.setId(1L);
        p1.setName("Notebook");
        p1.setPrice(100.99D);

        ProductPort p2 = new ProductPort();
        p2.setId(1L);
        p2.setName("Notebook");
        p2.setPrice(19.99D);
        OrderPort orderPort = new OrderPort(1L, null, List.of(p1, p2), LocalDateTime.of(2001, 2, 3, 4, 5), 0);
        Integer points = pointCalculator.calculate(orderPort);
        assertTrue(points == 90);
    }

    @Test
    public void transactionUnder50DollarsCalculatorTest() {
        ProductPort p1 = new ProductPort();
        p1.setId(1L);
        p1.setName("Notebook");
        p1.setPrice(49.99D);
        OrderPort orderPort = new OrderPort(1L, null, List.of(p1), LocalDateTime.of(20022, 8, 3, 4, 5), 0);
        Integer points = pointCalculator.calculate(orderPort);
        assertTrue(points == 0);
    }

}