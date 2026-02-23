package com.example.jpamnrelation.controller;

import com.example.jpamnrelation.dto.BookRequestDto;
import com.example.jpamnrelation.dto.BookResponseDto;
import com.example.jpamnrelation.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<BookResponseDto> saveBook(@RequestBody BookRequestDto dto) {
        return ResponseEntity.ok(bookService.saveBook(dto));
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookResponseDto> getBook (@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long bookId,
            @RequestBody BookRequestDto dto
    ) {
        return ResponseEntity.ok(bookService.updateBook(bookId, dto));
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }
}
