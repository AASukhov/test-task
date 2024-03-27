package com.example.demo.dto.response;

import lombok.Getter;

@Getter
public class CommonResponseDto {
    private Object result;

    public CommonResponseDto (Object result) {
        this.result = result;
    }
}
