package com.bednarz.shop.shopservice;

import com.bednarz.ports.OrderPointCalculator;
import com.bednarz.ports.model.OrderPort;
import com.bednarz.ports.model.ProductPort;
import org.springframework.stereotype.Service;

@Service
public class PointCalculator implements OrderPointCalculator {
    private final Integer firstLevel = 50;
    private final Integer secondLevel = 100;

    public Integer calculate(OrderPort orderPort) {
        Integer points = 0;
        Double transactionValue = orderPort.getEntries().stream()
                .map(ProductPort::getPrice).reduce(0D, (a, b) -> a + b);
        int value = transactionValue.intValue();
        if (value > firstLevel) {
            points+=value - 100 > 0 ? 50 : 100 - value;
        }
        if (value > secondLevel) {
            points += (value - secondLevel) * 2;
        }
        return points;
    }
}
