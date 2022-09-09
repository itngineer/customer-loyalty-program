package com.bednarz.domain;

import com.bednarz.domain.service.CustomerService;
import com.bednarz.ports.CustomerDataUseCase;
import com.bednarz.ports.model.CustomerInfo;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CustomerInfoAdapter implements CustomerDataUseCase {

    private final CustomerService customerService;

    @Override
    public List<CustomerInfo> getInfo() {
        return customerService.getAllCustomerDetails();
    }

    @Override
    public CustomerInfo getInfo(Long customerId) {
        return customerService.getDetails(customerId);
    }
}
