package com.bednarz.ports;

import com.bednarz.ports.model.OrderPort;

public interface TransactionRepositoryPort {
    void save(OrderPort order);
    void update(OrderPort order);
}
