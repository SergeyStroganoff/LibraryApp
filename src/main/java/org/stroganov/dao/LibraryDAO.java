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

    boolean addBookList(List<Book> bookList);

    boolean deleteBook(Book book);

    Book findBook(int numberISBN);

    List<Book> findBooks();

    boolean addUser(User user);

    User findUser(String userLogin);

    boolean deleteUser(String userLogin);

    boolean blockUser(String userLogin);

    boolean unblockUser(String userLogin);

    boolean addBookMark(BookMark bookMark);

    boolean deleteBookMark(BookMark bookMark);

    boolean addAuthor(Author author);

    Author findAuthor(String authorName);

    boolean deleteAuthorWithAllHisBooks(Author author);

}
