package com.example.demo.exception.factory;

import com.example.demo.exception.model.ContactInformationExceptionCode;

public class ContactInformationException extends RuntimeException{

    private final ContactInformationExceptionCode exceptionCode;

    public ContactInformationException(ContactInformationExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public ContactInformationExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
