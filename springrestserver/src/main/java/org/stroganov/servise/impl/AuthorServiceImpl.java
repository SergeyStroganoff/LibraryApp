package org.stroganov.servise.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroganov.entities.Author;
import org.stroganov.exceptions.AuthorDeleteException;
import org.stroganov.exceptions.AuthorSavingException;
import org.stroganov.models.AuthorDTO;
import org.stroganov.repository.AuthorRepository;
import org.stroganov.servise.AuthorService;
import org.stroganov.util.TransitionObjectsService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    public static final String AUTHOR_EXIST = "Such author exist";
    Logger logger = Logger.getLogger(AuthorServiceImpl.class);
    AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAuthor(AuthorDTO authorDTO) throws AuthorSavingException {
        if (!authorRepository.findAuthorByAuthorNameContaining(authorDTO.getAuthorName()).isEmpty()) {
            logger.info(AUTHOR_EXIST);
            throw new AuthorSavingException(AUTHOR_EXIST);
        }
        try {
            authorRepository.save(TransitionObjectsService.getAuthor(authorDTO));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new AuthorSavingException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteAuthor(String authorName) throws AuthorDeleteException {
        List<Author> authors = authorRepository.findAuthorByAuthorNameContaining(authorName);
        if (authors.isEmpty()) {
            logger.info("Автор не найден, удаление невозможно");
            throw new AuthorDeleteException(AUTHOR_EXIST);
        }
        try {
            authorRepository.delete(authors.get(0));
        } catch (Exception e) {
            throw new AuthorDeleteException(e.getMessage());
        }
    }

    @Override
    public List<Author> findAuthorByAuthorName(String authorName) {
        return authorRepository.findAuthorByAuthorNameContaining(authorName);
    }
}
