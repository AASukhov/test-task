package com.example.demo.exception.factory;

import com.example.demo.exception.model.ClientExceptionCode;

public class ClientException extends RuntimeException {
    private final ClientExceptionCode exceptionCode;

    public ClientException(ClientExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public ClientExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
