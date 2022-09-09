package com.bednarz.shop.infra.common;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ExceptionDto {

    LocalDateTime timestamp = LocalDateTime.now();
    String description;

}