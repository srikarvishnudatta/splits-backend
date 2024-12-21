package com.splits.backend.dtos;

import lombok.Data;

@Data
public class ResponseLoginDto {
    public String token;

    public ResponseLoginDto(String token) {
        this.token = token;
    }
}
