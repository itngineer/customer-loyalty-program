package com.bednarz.ports;

import com.bednarz.ports.model.CustomerInfo;

import java.util.List;

public interface CustomerRepositoryPort {
    CustomerInfo getCustomerInfo(Long customerId);
    List<CustomerInfo> getCustomersInfo();
}
