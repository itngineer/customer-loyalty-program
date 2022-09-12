package com.bednarz.domain.transaction;

import com.bednarz.domain.model.Product;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
    private Long customerId;
    private Long transactionId;
    private LocalDateTime transactionDate;
    private List<Product> entries;
    private Integer points;

}
