package org.stroganov.dao;

import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;

import java.util.List;

/**
 * Interface for defining functions with DB
 */
public interface LibraryDAO {
    boolean addBook(Book book);

    boolean deleteBook(Book book);

    Book findBook(String numberISBN);

    List<Book> findBooksByPartName(String partOfName);

    boolean addUser(User user);

    User findUser(String userLogin);

    boolean deleteUser(User user);

    boolean blockUser(User user);

    boolean unblockUser(User user);

    boolean addBookMark(BookMark bookMark);

    boolean deleteBookMark(BookMark bookMark);

    boolean addAuthor(Author author);

    Author findAuthor(String authorName);

    boolean deleteAuthorWithAllHisBooks(Author author);

    List<Book> findBooksByPartAuthorName(String partAuthorName);

    List<Book> findBooksByYearsRange(int firstYear, int secondYear);

    List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName);

    List<Book> findBooksWithUserBookMarks(User user);

}
