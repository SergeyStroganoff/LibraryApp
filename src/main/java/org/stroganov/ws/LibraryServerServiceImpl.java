package org.stroganov.ws;

import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.*;
import org.stroganov.util.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "org.stroganov.ws.LibraryService")
public class LibraryServerServiceImpl implements org.stroganov.ws.LibraryService {

    LibraryDAO libraryDAO = DataManager.getLibraryDAO();

    @Override
    public User[] getNewUser(@WebParam(name = "userName") String name) {
        ArrayList<User> stringArrayListUsers = new ArrayList<>();
        User user = new User(1, name, "admin", "sdf34rwf", false, true);
        User userNext = new User(1, "Иван", "admin", "sdf34rwf", false, true);
        stringArrayListUsers.add(user);
        stringArrayListUsers.add(userNext);
        return stringArrayListUsers.toArray(new User[0]);
    }

    @Override
    public boolean addBook(Book book) throws IOException {
        return libraryDAO.addBook(book);
    }

    @Override
    public boolean addBookList(Book[] bookArray) throws IOException {
        List<Book> bookList = List.of(bookArray);
        return libraryDAO.addBookList(bookList);
    }

    @Override
    public boolean deleteBook(Book book) throws IOException {
        return libraryDAO.deleteBook(book);
    }

    @Override
    public Book findBook(String numberISBN) {
        return libraryDAO.findBook(numberISBN);
    }

    @Override
    public Book[] findBooksByPartName(String partOfName) {
        return libraryDAO.findBooksByPartName(partOfName).toArray(new Book[0]);
    }

    @Override
    public boolean addUser(User user) throws IOException {
        return libraryDAO.addUser(user);
    }

    @Override
    public User findUser(String userLogin) {
        return libraryDAO.findUser(userLogin);
    }

    @Override
    public boolean deleteUser(User user) throws IOException {
        return libraryDAO.deleteUser(user);
    }

    @Override
    public boolean blockUser(User user) throws IOException {
        return libraryDAO.blockUser(user);
    }

    @Override
    public boolean unblockUser(User user) throws IOException {
        return libraryDAO.unblockUser(user);
    }

    @Override
    public boolean addBookMark(BookMark bookMark) throws IOException {
        return libraryDAO.addBookMark(bookMark);
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) throws IOException {
        return libraryDAO.deleteBookMark(bookMark);
    }

    @Override
    public boolean addAuthor(Author author) throws IOException {
        return libraryDAO.addAuthor(author);
    }

    @Override
    public Author findAuthor(String authorName) {
        return libraryDAO.findAuthor(authorName);
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) throws IOException {
        return libraryDAO.deleteAuthorWithAllHisBooks(author);
    }

    @Override
    public Book[] findBooksByPartAuthorName(String partAuthorName) {
        return libraryDAO.findBooksByPartAuthorName(partAuthorName).toArray(new Book[0]);
    }

    @Override
    public Book[] findBooksByYearsRange(int firstYear, int secondYear) {
        return libraryDAO.findBooksByYearsRange(firstYear, secondYear).toArray(new Book[0]);
    }

    @Override
    public Book[] findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        return libraryDAO.findBooksByParameters(bookYear, bookPages, partBookName).toArray(new Book[0]);
    }

    @Override
    public Book[] findBooksWithUserBookMarks(User user) {
        return libraryDAO.findBooksWithUserBookMarks(user).toArray(new Book[0]);
    }

    @Override
    public BookMark[] findUserBookMarks(User user) {
        return libraryDAO.findUserBookMarks(user).toArray(new BookMark[0]);
    }

    @Override
    public boolean addHistoryEvent(History history) {
        return libraryDAO.addHistoryEvent(history);
    }

    @Override
    public History[] getAllHistory() {
        return libraryDAO.getAllHistory().toArray(new History[0]);
    }
}
