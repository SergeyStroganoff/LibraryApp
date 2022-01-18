package org.stroganov.wsClient;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.stroganov.entities.*;

import java.io.IOException;

//Service Endpoint Interface
@WebService(name = "LibraryClientService", targetNamespace = "http://ws.stroganov.org/", endpointInterface = "org.stroganov.wsClient.LibraryServerServiceImplService")
//@HandlerChain(file = "M:\\PROGRAMMING\\EPAM\\EDUCATION\\Design_and_Architecture\\LibraryApp\\src\\main\\java\\org\\stroganov\\wsClient\\clientHandler.xml")
//@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface LibraryClientService {

    @WebMethod(operationName = "getNewUser")
    User[] getNewUser(@WebParam(name = "userName") String name);

    @WebMethod(operationName = "addBook")
    boolean addBook(Book book) throws IOException;

    @WebMethod(operationName = "addBookLIst")
    boolean addBookList(Book[] bookList) throws IOException;

    @WebMethod(operationName = "deleteBook")
    boolean deleteBook(Book book) throws IOException;

    @WebMethod(operationName = "findBook")
    Book findBook(String numberISBN);

    @WebMethod(operationName = "findBookByPartName")
    Book[] findBooksByPartName(String partOfName);

    @WebMethod(operationName = "addUser")
    boolean addUser(User user) throws IOException;

    @WebMethod(operationName = "findUser")
    User findUser(String userLogin);

    @WebMethod(operationName = "deleteUser")
    boolean deleteUser(User user) throws IOException;

    @WebMethod(operationName = "blockUser")
    boolean blockUser(User user) throws IOException;

    @WebMethod(operationName = "unblockUser")
    boolean unblockUser(User user) throws IOException;

    @WebMethod(operationName = "addBookMark")
    boolean addBookMark(BookMark bookMark) throws IOException;

    @WebMethod(operationName = "deleteBookMark")
    boolean deleteBookMark(BookMark bookMark) throws IOException;

    @WebMethod(operationName = "addAuthor")
    boolean addAuthor(Author author) throws IOException;

    @WebMethod(operationName = "findAuthor")
    Author findAuthor(String authorName);

    @WebMethod(operationName = "deleteAuthorWithAllHisBooks")
    boolean deleteAuthorWithAllHisBooks(Author author) throws IOException;

    @WebMethod(operationName = "findBooksByPartAuthorName")
    Book[] findBooksByPartAuthorName(String partAuthorName);

    @WebMethod(operationName = "findBooksByYearsRange")
    Book[] findBooksByYearsRange(int firstYear, int secondYear);

    @WebMethod(operationName = "findBooksByParameters")
    Book[] findBooksByParameters(int bookYear, int bookPages, String partBookName);

    @WebMethod(operationName = "findBooksWithUserBookMarks")
    Book[] findBooksWithUserBookMarks(User user);

    @WebMethod(operationName = "findUserBookMarks")
    BookMark[] findUserBookMarks(User user);

    @WebMethod(operationName = "addHistoryEvent")
    boolean addHistoryEvent(History history);

    @WebMethod(operationName = "getAllHistory")
    History[] getAllHistory();
}