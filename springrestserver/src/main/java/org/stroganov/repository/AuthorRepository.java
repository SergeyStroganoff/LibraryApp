package org.stroganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stroganov.entities.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

  //  Author findAuthorByAuthorNameContaining(String authorName);
    List<Author> findAuthorByAuthorNameContaining(String authorName);
}
