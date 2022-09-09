package com.bednarz.ports;

import com.bednarz.ports.model.CustomerInfo;
import com.bednarz.ports.model.OrderPort;

import java.util.List;

public interface CustomerDataUseCase {

    List<CustomerInfo> getInfo();

    CustomerInfo getInfo(Long customerId);
}
