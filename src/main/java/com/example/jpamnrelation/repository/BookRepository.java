package com.example.jpamnrelation.repository;

import com.example.jpamnrelation.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {
}
