package com.malik.CelioShop.CelioShop.playload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ErrorDetails {

    private ZonedDateTime timeStamp;
    private String message;

    private  String details;

    public ErrorDetails(ZonedDateTime timeStamp, String message,String details) {
        this.details = details;
        this.timeStamp = timeStamp;
        this.message = message;
    }
}
