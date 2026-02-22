package com.example.jpamnrelation.service;

import com.example.jpamnrelation.dto.AuthorRequestDto;
import com.example.jpamnrelation.dto.AuthorResponseDto;
import com.example.jpamnrelation.entity.Author;
import com.example.jpamnrelation.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public AuthorResponseDto saveAuthor(AuthorRequestDto dto) {
        if (dto.getName() == null) {
            throw new IllegalArgumentException("name이 필요합니다.");
        }

        Author author = new Author(dto.getName());
        Author savedAuthor = authorRepository.save(author);

        return new AuthorResponseDto(
                savedAuthor.getId(),
                savedAuthor.getName()
        );
    }

    @Transactional(readOnly = true)
    public AuthorResponseDto getAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new IllegalStateException("id에 맞는 author가 존재하지 않습니다.")
        );

        return new AuthorResponseDto(
                author.getId(),
                author.getName()
        );


    }

    public void deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new IllegalStateException("id에 맞는 author가 존재하지 않습니다.")
        );

        authorRepository.deleteById(authorId);
    }
}
