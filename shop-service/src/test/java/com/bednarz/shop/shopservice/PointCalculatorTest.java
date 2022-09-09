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
    public void shouldReturnCorrectPointValue() {
        ProductPort pp = new ProductPort();
        pp.setId(1L);
        pp.setName("Notebook");
        pp.setPrice(120D);
        OrderPort orderPort = new OrderPort(1L, List.of(pp), LocalDateTime.of(2001, 2, 3, 4, 5), 0);
        Integer points = pointCalculator.calculate(orderPort);
        assertTrue(points == 90);

    }

}