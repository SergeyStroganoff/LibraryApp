package org.stroganov.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.stroganov.entities.Author;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.History;

import java.io.IOException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)

public interface UserService {

    @WebMethod(operationName = "deleteAuthorWithAllHisBooks")
    boolean deleteAuthorWithAllHisBooks(Author author) throws IOException;

    @WebMethod(operationName = "addHistoryEvent")
    boolean addHistoryEvent(History history);

    @WebMethod(operationName = "deleteBookMark")
    boolean deleteBookMark(BookMark bookMark) throws IOException;

    @WebMethod(operationName = "addBookMark")
    boolean addBookMark(BookMark bookMark) throws IOException;


}
