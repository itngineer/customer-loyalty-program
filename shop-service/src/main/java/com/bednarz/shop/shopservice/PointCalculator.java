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
        Double transactionValue = orderPort.getEntries().stream()
                .map(ProductPort::getPrice).reduce(0D, (a, b) -> a + b);
        return transactionValueToPoints(transactionValue);
    }

    public Integer transactionValueToPoints(Double transactionValue) {
        Integer points = 0;
        int value = transactionValue.intValue();
        if (value > firstLevel) {
            points+=value - 100 > 0 ? 50 : value - firstLevel;
        }
        if (value > secondLevel) {
            points += (value - secondLevel) * 2;
        }
        return points;
    }

}
