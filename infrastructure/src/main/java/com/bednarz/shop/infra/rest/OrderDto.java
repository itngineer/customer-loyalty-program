package com.bednarz.shop.infra.rest;

import com.bednarz.ports.model.ProductPort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long customerId;
    private Long transactionId;
    private List<ProductPort> entries;
    private LocalDateTime transactionDate;

}
