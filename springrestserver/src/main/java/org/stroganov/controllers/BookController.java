package org.stroganov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stroganov.exceptions.AddBookException;
import org.stroganov.exceptions.BookNotExistException;
import org.stroganov.models.BookDTO;
import org.stroganov.servise.BookService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookService bookService;

    @DeleteMapping("book/{bookISBN}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookISBN") String bookISBN) throws BookNotExistException {
        bookService.delete(bookISBN);
        return ResponseEntity.ok().build();
    }

    @PostMapping("book/add")
    public ResponseEntity<String> addBook(BookDTO bookDTO) {
            bookService.addBook(bookDTO);
            return ResponseEntity.ok().build();
    }

    @PostMapping("book/addList")
    public ResponseEntity<String> addBooks(List<BookDTO> bookList) {
            bookService.addBooks(bookList);
            return ResponseEntity.ok().build();
    }

    @GetMapping("book/{numberISBN}")
    public ResponseEntity<BookDTO> findBook(@PathVariable("numberISBN") String numberISBN) throws BookNotExistException {
            BookDTO bookDTO = bookService.findBook(numberISBN);
            return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("book/partOfName/{partOfName}")
    public ResponseEntity<List<BookDTO>> findBooksByPartName(@PathVariable("partOfName") String partOfName) {
        List<BookDTO> bookDTOList = bookService.findBooksByPartName(partOfName);
        return ResponseEntity.ok(bookDTOList);
    }

    @GetMapping("book/partOfAuthorName/{partAuthorName}")
    public ResponseEntity<List<BookDTO>> findBooksByPartAuthorName(@PathVariable("partAuthorName") String partAuthorName) {
        List<BookDTO> bookDTOList = bookService.findBooksByPartAuthorName(partAuthorName);
        return ResponseEntity.ok(bookDTOList);
    }


    @GetMapping("book/findBooksByYearsRange")
    public ResponseEntity<List<BookDTO>> findBooksByYearsRange(@PathParam("firstYear") int firstYear, @PathParam("secondYear") int secondYear) {
        List<BookDTO> bookDTOList = bookService.findBooksByYearsRange(firstYear, secondYear);
        return ResponseEntity.ok(bookDTOList);
    }

    @GetMapping("book/findBooksByParameters")
    public ResponseEntity<List<BookDTO>> findBooksByParameters(@PathParam("bookYear") int bookYear, @PathParam("bookPages") int bookPages, @PathParam("partBookName") String partBookName) {
        List<BookDTO> bookDTOList = bookService.findBooksByParameters(bookYear, bookPages, partBookName);
        return ResponseEntity.ok(bookDTOList);
    }

    @GetMapping("book/findBooksWithUserBookMarks/{userLogin}")
    public ResponseEntity<List<BookDTO>> findBooksWithUserBookMarks(@PathVariable("userLogin") String userLogin) {
        List<BookDTO> bookDTOList = bookService.findBooksWithUserBookMarks(userLogin);
        return ResponseEntity.ok(bookDTOList);
    }
}
