package com.pj106.project_10_6.exception;

import com.pj106.project_10_6.errorcode.StatusCode;

public class RestApiException extends RuntimeException{

    // 필드값
    private final StatusCode statusCode;

    // getter
    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    // 생성자
    public RestApiException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

}
