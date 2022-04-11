package org.stroganov.servise;

import org.stroganov.exceptions.AddBookException;
import org.stroganov.exceptions.BookNotExistException;
import org.stroganov.models.BookDTO;

import java.util.List;

public interface BookService {
    void delete(String bookISBN) throws BookNotExistException;

    void addBook(BookDTO bookDTO) throws AddBookException;

    void addBooks(List<BookDTO> bookList) throws AddBookException;

    BookDTO findBook(String numberISBN) throws BookNotExistException;

    List<BookDTO> findBooksByPartName(String partOfName);

    List<BookDTO> findBooksByPartAuthorName(String partAuthorName);

    List<BookDTO> findBooksByYearsRange(int firstYear, int secondYear);

    List<BookDTO> findBooksByParameters(int bookYear, int bookPages, String partBookName);

    List<BookDTO> findBooksWithUserBookMarks(String userLogin);
}
