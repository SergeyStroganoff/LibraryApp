package org.stroganov.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;

import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface SearchService {
    @WebMethod(operationName = "findBook")
    Book findBook(String numberISBN);

    @WebMethod(operationName = "findAuthor")
    Author findAuthor(String authorName);

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

    @WebMethod(operationName = "findUser")
    User findUser(String userLogin);

    @WebMethod(operationName = "findBooksByPartName")
    Book[] findBooksByPartName(String partOfName);
}
