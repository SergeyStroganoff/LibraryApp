package org.stroganov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.stroganov.entities.BookMark;
import org.stroganov.exceptions.BookMarkNotExistException;
import org.stroganov.exceptions.BookMarkSavingException;
import org.stroganov.exceptions.UserNotExistException;
import org.stroganov.models.BookMarkDTO;
import org.stroganov.servise.BookMarkService;

import java.util.List;

@Controller
@RequestMapping("/api")
public class BookMarkController {

    public static final String MARK_WAS_DELETED_MESSAGE = "BookMark was deleted";
    @Autowired
    BookMarkService bookMarkService;

    @DeleteMapping("bookmark/{id}")
    //@JWTTokenNeeded
    public ResponseEntity<String> deleteBookMark(@PathVariable("id") int id) {
        try {
            bookMarkService.deleteBookMark(id);
            return ResponseEntity.ok(MARK_WAS_DELETED_MESSAGE);
        } catch (BookMarkNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("bookmark/")
    // @JWTTokenNeeded
    public ResponseEntity addBookMark(BookMarkDTO bookMarkDTO) {
        try {
            bookMarkService.addBookMark(bookMarkDTO);
            return ResponseEntity.ok().build();
        } catch (BookMarkSavingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("bookmark/{userLogin}")
    //@JWTTokenNeeded
    public ResponseEntity findUsersBookMarks(@PathVariable("userLogin") String userLogin) {
        try {
            List<BookMark> bookMarks =  bookMarkService.findUsersBookMarks(userLogin);
            return ResponseEntity.ok(bookMarks);
        } catch (UserNotExistException e) {
           return ResponseEntity.badRequest().build();
        }
    }
}
