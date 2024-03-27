package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClientResponseDto {
    private int clientId;
    private String login;
}
