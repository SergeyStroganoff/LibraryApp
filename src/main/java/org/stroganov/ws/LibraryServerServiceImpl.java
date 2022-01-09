package org.stroganov.ws;

import jakarta.jws.WebMethod;
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
    @WebMethod(operationName = "getSimple")
    public String getHelloWorldAsString(String name) {
        return name;
    }

    @Override
    @WebMethod(operationName = "getHelloList")
    public ArrayList<String> returnHelloList() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("Hello - I'm Library Service");
        return stringArrayList;
    }

    @Override
    @WebMethod(operationName = "addBook")
    public boolean addBook(@WebParam(name = "bookEntity") Book book) throws IOException {
        return libraryDAO.addBook(book);
    }

    //////////////

    @Override
    @WebMethod(operationName = "addBookList")
    public boolean addBookList(@WebParam(name = "bookEntityList") List<Book> bookList) throws IOException {
        return libraryDAO.addBookList(bookList);
    }

    @Override
    @WebMethod(operationName = "deleteBook")
    public boolean deleteBook(@WebParam(name = "bookEntity") Book book) throws IOException {
        return libraryDAO.deleteBook(book);
    }

    @Override
    @WebMethod(operationName = "findBook")
    public Book findBook(@WebParam(name = "numberISBN") String numberISBN) {
        return libraryDAO.findBook(numberISBN);
    }
    //////////////

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

    @Override
    public List<BookMark> findUserBookMarks(User user) {
        return null;
    }

    @Override
    public boolean addHistoryEvent(History history) {
        return false;
    }

    @Override
    public List<History> getAllHistory() {
        return null;
    }
}
