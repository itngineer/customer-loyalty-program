package com.bednarz.ports;

import com.bednarz.ports.model.OrderPort;

public interface PlaceOrderUseCase {

    void place(OrderPort orderPort);
    void change(OrderPort orderPort);
}
