package org.stroganov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stroganov.entities.BookMark;
import org.stroganov.exceptions.BookMarkServiceException;
import org.stroganov.exceptions.UserNotExistException;
import org.stroganov.models.BookMarkDTO;
import org.stroganov.servise.BookMarkService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookMarkController {

    public static final String MARK_WAS_DELETED_MESSAGE = "BookMark was deleted";
    @Autowired
    BookMarkService bookMarkService;

    @DeleteMapping("bookmark/{id}")
    public ResponseEntity<String> deleteBookMark(@PathVariable("id") int id) throws BookMarkServiceException {
        bookMarkService.deleteBookMark(id);
        return ResponseEntity.ok(MARK_WAS_DELETED_MESSAGE);
    }

    @PostMapping("bookmark/")
    public ResponseEntity addBookMark(BookMarkDTO bookMarkDTO) throws BookMarkServiceException {
        bookMarkService.addBookMark(bookMarkDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("bookmark/{userLogin}")
    public ResponseEntity findUsersBookMarks(@PathVariable("userLogin") String userLogin) throws UserNotExistException {
        List<BookMark> bookMarks = bookMarkService.findUsersBookMarks(userLogin);
        return ResponseEntity.ok(bookMarks);
    }
}
