package com.example.demo.exception.model;

import org.springframework.http.HttpStatus;

public enum ContactInformationExceptionCode {

    EMAIL_IN_USE(HttpStatus.BAD_REQUEST, "Этот почтовый адрес уже занят"),
    EMAIL_IS_NULL(HttpStatus.BAD_REQUEST, "Электронная почта не заполнена"),
    PHONE_IN_USE(HttpStatus.BAD_REQUEST, "Этот номер телефона уже занят"),
    PHONE_IS_NULL(HttpStatus.BAD_REQUEST, "Номер телефона не заполнен"),
    UNRECOGNIZED_TYPE(HttpStatus.NOT_FOUND, "Неопознанный тип данных");

    private final HttpStatus httpStatus;
    private final String message;

    ContactInformationExceptionCode(HttpStatus httpStatus, String message) {
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
