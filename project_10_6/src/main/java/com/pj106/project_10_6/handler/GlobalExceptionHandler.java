package com.pj106.project_10_6.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GlobalExceptionHandler {
    private String msg;
    private HttpStatus statusCode;

    public GlobalExceptionHandler(String msg, HttpStatus statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}

