package org.stroganov.dao;

import org.stroganov.JsonDBAPI.JsonDBLoader;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UnrealizedFunctionalityException;
import java.util.List;

public class JsonDataSource implements LibraryDAO {

    private static JsonDataSource instance;
    private final JsonDBLoader jsonDBLoader = new JsonDBLoader();
    List<Book> bookList;
    List<User> userList;

    public static synchronized JsonDataSource getInstance() throws UnrealizedFunctionalityException {
        if (instance == null) {
            instance = new JsonDataSource();
        }
        return instance;
    }

    private JsonDataSource() throws UnrealizedFunctionalityException {
        bookList = jsonDBLoader.loadBooks();
        userList = jsonDBLoader.loadUsers();
    }

    @Override
    public boolean addBook(Book book) {
        return false;
    }

    @Override
    public boolean addBookList(List<Book> bookList) {
        return false;
    }

    @Override
    public boolean deleteBook(Book book) {
        return false;
    }

    @Override
    public Book findBook() throws UnrealizedFunctionalityException {
        throw new UnrealizedFunctionalityException("findBook not realized");
    }

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public User findUser(String userLogin) throws UnrealizedFunctionalityException {
        throw new UnrealizedFunctionalityException("findUser not realized");
    }

    @Override
    public boolean deleteUser(String userLogin) {
        return false;
    }

    @Override
    public boolean blockUser(String userLogin) {
        return false;
    }

    @Override
    public boolean unblockUser(String userLogin) {
        return false;
    }
}
