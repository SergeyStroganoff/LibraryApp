package org.stroganov.dao;

import org.stroganov.App;
import org.stroganov.JsonDBAPI.JsonDBLoader;
import org.stroganov.JsonDBAPI.JsonSerializer;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.FileUtil;

import java.util.List;

public class JsonDataSource implements LibraryDAO {

    private static JsonDataSource instance;
    private List<Book> bookList;
    private List<User> userList;
    private List<BookMark> bookMarkListList;
    private List<Author> authorsList;
    p

    public static synchronized JsonDataSource getInstance() throws DBExceptions {
        if (instance == null) {
            instance = new JsonDataSource();
        }
        return instance;
    }

    private JsonDataSource() throws DBExceptions {
        JsonDBLoader jsonDBLoader = new JsonDBLoader();
        bookList = jsonDBLoader.loadBooks();
        userList = jsonDBLoader.loadUsers();
        bookMarkListList = jsonDBLoader.loadBookMarks();
        authorsList = jsonDBLoader.loadAuthors();
    }

    @Override
    public boolean addBook(Book book) {
        if (!bookList.contains(book)) {
            bookList.add(book);
            папап
            if (!authorsList.contains(book.getAuthor())) {
                authorsList.add(book.getAuthor());
            }
        }

        return false;
    }

    @Override
    public boolean deleteBook(Book book) {
        return false;
    }

    @Override
    public Book findBook(int numberISBN) {
        return null;
    }

    @Override
    public List<Book> findBooks() {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public User findUser(String userLogin) {
        return null;
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

    @Override
    public boolean addBookMark(BookMark bookMark) {
        return false;
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) {
        return false;
    }

    @Override
    public boolean addAuthor(Author author) {
        return false;
    }

    @Override
    public Author findAuthor(String authorName) {
        return null;
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) {
        return false;
    }
}
