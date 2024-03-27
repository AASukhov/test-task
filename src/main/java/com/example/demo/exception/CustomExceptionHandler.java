package com.example.demo.exception;

import com.example.demo.dto.response.CommonResponseDto;
import com.example.demo.exception.factory.ClientException;
import com.example.demo.exception.factory.ContactInformationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<CommonResponseDto> clientExceptionHandle(ClientException exception) {
        return new ResponseEntity<>(commonResponseWrapper(exception.getMessage()),
                exception.getExceptionCode().getHttpStatus());
    }

    @ExceptionHandler(ContactInformationException.class)
    public ResponseEntity<CommonResponseDto> phoneExceptionHandle(ContactInformationException exception) {
        return new ResponseEntity<>(commonResponseWrapper(exception.getMessage()),
                exception.getExceptionCode().getHttpStatus());
    }

    private CommonResponseDto commonResponseWrapper(String message) {
        return new CommonResponseDto(message);
    }
}