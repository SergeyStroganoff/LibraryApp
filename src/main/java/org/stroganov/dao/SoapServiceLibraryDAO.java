package org.stroganov.dao;

import jakarta.xml.ws.Service;
import org.stroganov.entities.*;
import org.stroganov.ws.LibraryService;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class SoapServiceLibraryDAO implements LibraryDAO {

    private static SoapServiceLibraryDAO instance;
    private final LibraryService libraryService;

    public SoapServiceLibraryDAO() throws MalformedURLException {
        QName qname = new QName("http://ws.stroganov.org/", "LibraryServerServiceImplService");
        // settings
        URL url = new URL("http://127.0.0.1:8082/LibraryService/services?wsdl");
        Service service = Service.create(url, qname);
        libraryService = service.getPort(LibraryService.class);
    }

    public static synchronized SoapServiceLibraryDAO getInstance() throws MalformedURLException {
        if (instance == null) {
            instance = new SoapServiceLibraryDAO();
        }
        return instance;
    }

    @Override
    public boolean addBook(Book book) throws IOException {
        return libraryService.addBook(book);

    }

    @Override
    public boolean addBookList(List<Book> bookList) throws IOException {
        return libraryService.addBookList(bookList.toArray(new Book[0]));
    }

    @Override
    public boolean deleteBook(Book book) throws IOException {
        return libraryService.deleteBook(book);
    }

    @Override
    public Book findBook(String numberISBN) {
        return libraryService.findBook(numberISBN);
    }

    @Override
    public List<Book> findBooksByPartName(String partOfName) {
        Book[] books = libraryService.findBooksByPartName(partOfName);
        return Arrays.asList(books);
    }

    @Override
    public boolean addUser(User user) throws IOException {
        return libraryService.addUser(user);
    }

    @Override
    public User findUser(String userLogin) {
        return libraryService.findUser(userLogin);
    }

    @Override
    public boolean deleteUser(User user) throws IOException {
        return libraryService.deleteUser(user);
    }

    @Override
    public boolean blockUser(User user) throws IOException {
        return libraryService.blockUser(user);
    }

    @Override
    public boolean unblockUser(User user) throws IOException {
        return libraryService.unblockUser(user);
    }

    @Override
    public boolean addBookMark(BookMark bookMark) throws IOException {
        return libraryService.addBookMark(bookMark);
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) throws IOException {
        return libraryService.deleteBookMark(bookMark);
    }

    @Override
    public boolean addAuthor(Author author) throws IOException {
        return libraryService.addAuthor(author);
    }

    @Override
    public Author findAuthor(String authorName) {
        return libraryService.findAuthor(authorName);
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) throws IOException {
        return libraryService.deleteAuthorWithAllHisBooks(author);
    }

    @Override
    public List<Book> findBooksByPartAuthorName(String partAuthorName) {
        Book[] books = libraryService.findBooksByPartAuthorName(partAuthorName);
        return Arrays.asList(books);
    }

    @Override
    public List<Book> findBooksByYearsRange(int firstYear, int secondYear) {
        Book[] books = libraryService.findBooksByYearsRange(firstYear, secondYear);
        return Arrays.asList(books);
    }

    @Override
    public List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        Book[] books = libraryService.findBooksByParameters(bookYear, bookPages, partBookName);
        return Arrays.asList(books);
    }

    @Override
    public List<Book> findBooksWithUserBookMarks(User user) {
        Book[] books = libraryService.findBooksWithUserBookMarks(user);
        return Arrays.asList(books);
    }

    @Override
    public List<BookMark> findUserBookMarks(User user) {
        BookMark[] books = libraryService.findUserBookMarks(user);
        return Arrays.asList(books);
    }

    @Override
    public boolean addHistoryEvent(History history) {
        return libraryService.addHistoryEvent(history);
    }

    @Override
    public List<History> getAllHistory() {
        History[] books = libraryService.getAllHistory();
        return Arrays.asList(books);
    }
}
