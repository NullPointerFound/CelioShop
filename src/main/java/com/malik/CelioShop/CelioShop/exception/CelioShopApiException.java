package com.malik.CelioShop.CelioShop.exception;

import org.springframework.http.HttpStatus;

public class CelioShopApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public CelioShopApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CelioShopApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
