package com.example.jpamnrelation.repository;

import com.example.jpamnrelation.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository <Author, Long>{
}
