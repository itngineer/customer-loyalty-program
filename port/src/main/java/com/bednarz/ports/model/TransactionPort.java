package com.bednarz.ports.model;

import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class TransactionPort {
    private Double transactionValue;
    private LocalDateTime transactionDate;
    private Integer points;
}
