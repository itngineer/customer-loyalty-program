package com.bednarz.ports;

import com.bednarz.ports.model.OrderPort;

public interface CustomerDataUseCase {

    void getInfo();

    void getInfo(Long customerId);
}
