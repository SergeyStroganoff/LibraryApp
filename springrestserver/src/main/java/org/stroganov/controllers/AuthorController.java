package org.stroganov.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stroganov.entities.Author;
import org.stroganov.exceptions.AuthorDeleteException;
import org.stroganov.exceptions.AuthorSavingException;
import org.stroganov.models.AuthorDTO;
import org.stroganov.repository.AuthorRepository;
import org.stroganov.servise.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorService authorService;

    @PostMapping("author/")
    //@JWTTokenNeeded
    public ResponseEntity addAuthor(@RequestBody AuthorDTO authorDTO) {
        try {
            authorService.saveAuthor(authorDTO);
            return ResponseEntity.ok("Author saved");
        } catch (AuthorSavingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("author/{authorName}")
    //@JWTTokenNeeded
    public ResponseEntity findAuthor(@PathVariable("authorName") String authorName) {
        List<Author> authors = authorRepository.findAuthorByAuthorNameContaining(authorName);
        if (authors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        AuthorDTO authorDTO = new AuthorDTO(authors.get(0));
        return ResponseEntity.ok(authorDTO);
    }

    @DeleteMapping("author/{authorName}")
    //@JWTTokenNeeded
    public ResponseEntity deleteAuthorWithAllHisBooks(@PathVariable("authorName") String authorName) {
        try {
            authorService.deleteAuthor(authorName);
            return ResponseEntity.ok("Author deleted");
        } catch (AuthorDeleteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
