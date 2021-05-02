package com.ait.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShopNotFoundException extends Exception{
    String message;
    public ShopNotFoundException(String message) {
        this.message = message;
    }
}
