package org.stroganov.servise;

import org.springframework.stereotype.Service;
import org.stroganov.entities.Author;
import org.stroganov.exceptions.AuthorServiceException;
import org.stroganov.models.AuthorDTO;

import java.util.List;

@Service
public interface AuthorService {

    void saveAuthor(AuthorDTO authorDTO) throws AuthorServiceException;

    void deleteAuthor(String authorName) throws AuthorServiceException;

    List<Author> findAuthorByAuthorName(String authorName);
}
