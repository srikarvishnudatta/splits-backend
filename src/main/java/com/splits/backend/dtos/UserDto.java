package com.splits.backend.dtos;

import lombok.Data;

@Data
public class UserDto {
    String username;
    String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
