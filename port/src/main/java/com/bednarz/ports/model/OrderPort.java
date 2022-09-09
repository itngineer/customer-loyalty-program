package com.bednarz.ports.model;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class OrderPort {
    Long customerId;
    List<ProductPort> entries;
    LocalDateTime transactionDate;
    Integer points;
}


