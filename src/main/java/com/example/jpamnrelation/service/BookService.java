package com.example.jpamnrelation.service;

import com.example.jpamnrelation.dto.AuthorResponseDto;
import com.example.jpamnrelation.dto.BookRequestDto;
import com.example.jpamnrelation.dto.BookResponseDto;
import com.example.jpamnrelation.entity.Author;
import com.example.jpamnrelation.entity.Book;
import com.example.jpamnrelation.entity.BookAuthor;
import com.example.jpamnrelation.repository.AuthorRepository;
import com.example.jpamnrelation.repository.BookAuthorRepository;
import com.example.jpamnrelation.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorRepository bookAuthorRepository;

    @Transactional
    public BookResponseDto saveBook(BookRequestDto dto) {
        if (dto.getTitle() == null) {
            throw new IllegalArgumentException("title이 필요합니다.");
        }

        Book book = new Book(dto.getTitle());
        Book savedBook = bookRepository.save(book);

        // 각 author id에 대해 BookAuthor 중간 테이블에 저장
        if (dto.getAuthorIds() != null) {
            for (Long authorId : dto.getAuthorIds()) {
                Author author = authorRepository.findById(authorId).orElseThrow(
                        () -> new IllegalStateException("id에 맞는 author가 존재하지 않습니다.")
                );

                BookAuthor bookAuthor = new BookAuthor(savedBook, author);
                bookAuthorRepository.save(bookAuthor);
            }
        }

        return convertToResponseDto(savedBook);
    }


    private BookResponseDto convertToResponseDto (Book book) {
        List<AuthorResponseDto> authors = bookAuthorRepository.findByBook(book).stream()
                .map(ba -> new AuthorResponseDto(ba.getAuthor().getId(), ba.getAuthor().getName()))
                .collect(Collectors.toList());
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                authors
        );
    }

    @Transactional(readOnly = true)
    public BookResponseDto getBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new IllegalStateException("id에 맞는 book이 존재하지 않습니다.")
        );

        return convertToResponseDto(book);
    }

    @Transactional
    public BookResponseDto updateBook(Long bookId, BookRequestDto dto) {
        Book book = null;
        try {
            book = bookRepository.findById(bookId).orElseThrow(
                    () -> new IllegalStateException("id에 맞는 book이 존재하지 않습니다.")
            );
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }

        // 요청에 title이 포함되어 있다면 업데이트
        if (dto.getTitle() != null) {
            book.updateTitle(dto.getTitle());
        }

        // 요청에 authorIds가 포함되어 있다면 기존 매핑을 삭제후 재설정
        if (dto.getAuthorIds() != null) {
            // 기존 BookAuthor 매핑 삭제
            bookAuthorRepository.deleteByBook(book);

            // 요청에 포함된 각 authorId에 대해 새로운 매핑 생성
            for (Long authorId : dto.getAuthorIds()) {
                Author author = authorRepository.findById(authorId).orElseThrow(
                        () -> new EntityNotFoundException("id에 맞는 author가 존재하지 않습니다.")
                );

                BookAuthor bookAuthor = new BookAuthor(book, author);
                bookAuthorRepository.save(bookAuthor);
            }
        }

        Book updatedBook = bookRepository.save(book);
        return convertToResponseDto(updatedBook);
    }

    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new IllegalStateException("id에 맞는 book이 존재하지 않습니다.")
        );

        bookAuthorRepository.deleteByBook(book);
        bookRepository.delete(book);
    }
}
