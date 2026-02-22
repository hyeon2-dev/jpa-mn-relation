package com.example.jpamnrelation.controller;

import com.example.jpamnrelation.dto.AuthorRequestDto;
import com.example.jpamnrelation.dto.AuthorResponseDto;
import com.example.jpamnrelation.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/authors")
    public ResponseEntity<AuthorResponseDto> saveAuthor (@RequestBody AuthorRequestDto dto) {
        return ResponseEntity.ok(authorService.saveAuthor(dto));
    }

    @GetMapping("/authors/{authorId}")
    public ResponseEntity<AuthorResponseDto> getAuthor (@PathVariable Long authorId) {
        return ResponseEntity.ok(authorService.getAuthor(authorId));
    }

    @DeleteMapping("/authors/{authorId}")
    public void deleteAuthor (@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
    }

    // 저자가 쓴 책 조회
    @GetMapping("/authors/{authorId}/books")
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors () {}


}
