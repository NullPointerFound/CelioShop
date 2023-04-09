package com.malik.CelioShop.CelioShop.exception;

import org.springframework.http.HttpStatus;

public class CelioShopApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public CelioShopApiException(String message,HttpStatus status) {
        super(String.format("%s",message));
        this.message = message;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
