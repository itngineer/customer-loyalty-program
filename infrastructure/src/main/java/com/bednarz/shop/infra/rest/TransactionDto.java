package com.bednarz.shop.infra.rest;

import com.bednarz.ports.model.ProductPort;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Setter
@AllArgsConstructor
@ToString
public class TransactionDto {
    private Double transactionValue;
    private LocalDateTime transactionDate;
    private Integer points;

}
