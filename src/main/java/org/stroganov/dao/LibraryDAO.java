package org.stroganov.dao;

import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UnrealizedFunctionalityException;
import java.util.List;

/**
 * Интерфейс для определения функций с DB
 */
public interface LibraryDAO {
    boolean addBook(Book book);

    boolean addBookList(List<Book> bookList);

    boolean deleteBook(Book book);

    Book findBook() throws UnrealizedFunctionalityException;

    boolean saveUser(User user);

    User findUser(String userLogin) throws UnrealizedFunctionalityException;

    boolean deleteUser(String userLogin);

    boolean blockUser(String userLogin);

    boolean unblockUser(String userLogin);
}
