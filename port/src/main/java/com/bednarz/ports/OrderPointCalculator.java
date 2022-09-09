package com.bednarz.ports;

import com.bednarz.ports.model.OrderPort;

public interface OrderPointCalculator {
    Integer calculate(OrderPort orderPort);
}
