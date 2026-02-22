package com.example.jpamnrelation.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long id;
    private final String email;

    public UserResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
