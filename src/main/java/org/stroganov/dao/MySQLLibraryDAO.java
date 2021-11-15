package org.stroganov.dao;

import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.DBExceptions;

import java.io.IOException;
import java.util.List;

public class MySQLLibraryDAO implements LibraryDAO {

    private static MySQLLibraryDAO instance;

    public static synchronized MySQLLibraryDAO getInstance() throws DBExceptions {
        if (instance == null) {
            instance = new MySQLLibraryDAO();
        }
        return instance;
    }

    @Override
    public boolean addBook(Book book) throws IOException {
        return false;
    }

    @Override
    public boolean addBook(List<Book> bookList) throws IOException {
        return false;
    }

    @Override
    public boolean deleteBook(Book book) throws IOException {
        return false;
    }

    @Override
    public Book findBook(String numberISBN) {
        return null;
    }

    @Override
    public List<Book> findBooksByPartName(String partOfName) {
        return null;
    }

    @Override
    public boolean addUser(User user) throws IOException {
        return false;
    }

    @Override
    public User findUser(String userLogin) {
        return null;
    }

    @Override
    public boolean deleteUser(User user) throws IOException {
        return false;
    }

    @Override
    public boolean blockUser(User user) throws IOException {
        return false;
    }

    @Override
    public boolean unblockUser(User user) throws IOException {
        return false;
    }

    @Override
    public boolean addBookMark(BookMark bookMark) throws IOException {
        return false;
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) throws IOException {
        return false;
    }

    @Override
    public boolean addAuthor(Author author) throws IOException {
        return false;
    }

    @Override
    public Author findAuthor(String authorName) {
        return null;
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) throws IOException {
        return false;
    }

    @Override
    public List<Book> findBooksByPartAuthorName(String partAuthorName) {
        return null;
    }

    @Override
    public List<Book> findBooksByYearsRange(int firstYear, int secondYear) {
        return null;
    }

    @Override
    public List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        return null;
    }

    @Override
    public List<Book> findBooksWithUserBookMarks(User user) {
        return null;
    }
}
