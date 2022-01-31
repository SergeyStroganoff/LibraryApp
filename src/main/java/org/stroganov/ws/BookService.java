package org.stroganov.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;

import java.io.IOException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface BookService {

    @WebMethod(operationName = "addBook")
    boolean addBook(Book book) throws IOException;

    @WebMethod(operationName = "addBookLIst")
    boolean addBookList(Book[] bookList) throws IOException;

    @WebMethod(operationName = "deleteBook")
    boolean deleteBook(Book book) throws IOException;

    @WebMethod(operationName = "addAuthor")
    boolean addAuthor(Author author) throws IOException;

}
