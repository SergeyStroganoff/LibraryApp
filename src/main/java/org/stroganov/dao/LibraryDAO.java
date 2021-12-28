package org.stroganov.dao;

import org.stroganov.entities.*;

import java.io.IOException;
import java.util.List;

/**
 * Interface for defining functions with DB
 */
public interface LibraryDAO {
    boolean addBook(Book book) throws IOException;

    boolean addBook(List<Book> bookList) throws IOException;

    boolean deleteBook(Book book) throws IOException;

    Book findBook(String numberISBN);

    List<Book> findBooksByPartName(String partOfName);

    boolean addUser(User user) throws IOException;

    User findUser(String userLogin);

    boolean deleteUser(User user) throws IOException;

    boolean blockUser(User user) throws IOException;

    boolean unblockUser(User user) throws IOException;

    boolean addBookMark(BookMark bookMark) throws IOException;

    boolean deleteBookMark(BookMark bookMark) throws IOException;

    boolean addAuthor(Author author) throws IOException;

    Author findAuthor(String authorName);

    boolean deleteAuthorWithAllHisBooks(Author author) throws IOException;

    List<Book> findBooksByPartAuthorName(String partAuthorName);

    List<Book> findBooksByYearsRange(int firstYear, int secondYear);

    List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName);

    List<Book> findBooksWithUserBookMarks(User user);

    List<BookMark> findUserBookMarks(User user);

    boolean addHistoryEvent(History history);

    List<History> getAllHistory();
}
