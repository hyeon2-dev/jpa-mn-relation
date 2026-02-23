package com.example.jpamnrelation.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BookResponseDto {

    private final Long id;
    private final String title;
    private final List<AuthorResponseDto> authors;

    public BookResponseDto(Long id, String title, List<AuthorResponseDto> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }
}
