package com.bednarz.shop.infra.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class WrongDataException extends ResponseStatusException {
    public WrongDataException(String message){
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
    @Override
    public HttpHeaders getResponseHeaders() {
        return new HttpHeaders();
    }

}

