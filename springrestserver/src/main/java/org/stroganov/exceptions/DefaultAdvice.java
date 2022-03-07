
package org.stroganov.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(AuthorServiceException.class)
    public ResponseEntity<String> handleAuthorServiceException(AuthorServiceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BookMarkServiceException.class)
    public ResponseEntity<String> handleAuthorServiceException(BookMarkServiceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<String> handleUserNotExistException(UserNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotExistException.class)
    public ResponseEntity<String> handleBookNotExistException(BookNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AddBookException.class, HistorySavingException.class})
    public ResponseEntity<String> handleAddBookException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
