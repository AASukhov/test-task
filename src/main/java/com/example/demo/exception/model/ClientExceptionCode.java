package com.example.demo.exception.model;

import org.springframework.http.HttpStatus;

public enum ClientExceptionCode {

    LOGIN_IN_USE(HttpStatus.BAD_REQUEST, "Этот логин уже занят"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Клиент с этим id не найден");

    private final HttpStatus httpStatus;
    private final String message;

    ClientExceptionCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
