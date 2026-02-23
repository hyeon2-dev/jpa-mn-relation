package com.example.jpamnrelation.repository;

import com.example.jpamnrelation.entity.Author;
import com.example.jpamnrelation.entity.Book;
import com.example.jpamnrelation.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BookAuthorRepository extends JpaRepository <BookAuthor, Long> {
    List<BookAuthor> findByBook(Book book);
    void deleteByBook(Book book);
    List<BookAuthor> findByAuthor(Author author);
}
