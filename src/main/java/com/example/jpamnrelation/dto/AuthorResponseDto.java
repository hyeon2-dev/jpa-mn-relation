package com.example.jpamnrelation.dto;

import lombok.Getter;

@Getter
public class AuthorResponseDto {

    private final Long id;
    private final String name;

    public AuthorResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
