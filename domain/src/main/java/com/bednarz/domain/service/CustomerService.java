package com.bednarz.domain.service;


import com.bednarz.ports.CustomerRepositoryPort;
import com.bednarz.ports.model.CustomerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.List;

@Log
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CustomerInfo getDetails(Long customerId){
        return customerRepositoryPort.getCustomerInfo(customerId);
    }

    public List<CustomerInfo> getAllCustomerDetails(){
        return customerRepositoryPort.getCustomersInfo();
    }

}
