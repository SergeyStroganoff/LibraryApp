package org.stroganov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stroganov.entities.Author;
import org.stroganov.exceptions.AuthorServiceException;
import org.stroganov.models.AuthorDTO;
import org.stroganov.servise.impl.AuthorServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorService;

    @PostMapping("author/")
    public ResponseEntity addAuthor(@RequestBody AuthorDTO authorDTO) throws AuthorServiceException {
        authorService.saveAuthor(authorDTO);
        return ResponseEntity.ok("Author saved");
    }

    @GetMapping("author/{authorName}")
    public ResponseEntity findAuthor(@PathVariable("authorName") String authorName) {
        List<Author> authors = authorService.findAuthorByAuthorName(authorName);
        if (authors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        AuthorDTO authorDTO = new AuthorDTO(authors.get(0));
        return ResponseEntity.ok(authorDTO);
    }

    @DeleteMapping("author/{authorName}")
    public ResponseEntity deleteAuthorWithAllHisBooks(@PathVariable("authorName") String authorName) throws AuthorServiceException {
        authorService.deleteAuthor(authorName);
        return ResponseEntity.ok("Author deleted");
    }
}
