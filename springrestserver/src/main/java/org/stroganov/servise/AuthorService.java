package org.stroganov.servise;

import org.springframework.stereotype.Service;
import org.stroganov.entities.Author;
import org.stroganov.exceptions.AuthorDeleteException;
import org.stroganov.exceptions.AuthorSavingException;
import org.stroganov.models.AuthorDTO;

import java.util.List;

@Service
public interface AuthorService {

    void saveAuthor(AuthorDTO authorDTO) throws AuthorSavingException;

    void deleteAuthor(String authorName) throws AuthorDeleteException;

    List<Author> findAuthorByAuthorName(String authorName);
}
