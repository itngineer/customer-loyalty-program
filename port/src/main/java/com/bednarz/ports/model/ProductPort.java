package com.bednarz.ports.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductPort {
    private Long id;
    private String name;
    private Double price;
}
