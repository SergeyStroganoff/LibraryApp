
package org.stroganov.wsClient;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.User;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebService(name = "SearchService", targetNamespace = "http://ws.stroganov.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
        ObjectFactory.class
})
public interface SearchServiceClient {


    /**
     * @param arg0
     * @return returns org.stroganov.ws.User
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/SearchService/findUserRequest", output = "http://ws.stroganov.org/SearchService/findUserResponse")
    public User findUser(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0);

    /**
     * @param arg0
     * @return returns org.stroganov.ws.Book
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/SearchService/findBookRequest", output = "http://ws.stroganov.org/SearchService/findBookResponse")
    public Book findBook(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0);

    /**
     * @param arg0
     * @return returns org.stroganov.ws.Author
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/SearchService/findAuthorRequest", output = "http://ws.stroganov.org/SearchService/findAuthorResponse")
    public Author findAuthor(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0);

    /**
     * @param arg0
     * @return returns org.stroganov.ws.BookMarkArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/SearchService/findUserBookMarksRequest", output = "http://ws.stroganov.org/SearchService/findUserBookMarksResponse")
    public BookMarkArray findUserBookMarks(
            @WebParam(name = "arg0", partName = "arg0")
                    User arg0);

    /**
     * @param arg0
     * @return returns org.stroganov.ws.BookArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/SearchService/findBooksByPartNameRequest", output = "http://ws.stroganov.org/SearchService/findBooksByPartNameResponse")
    public BookArray findBooksByPartName(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0);

    /**
     * @param arg0
     * @return returns org.stroganov.ws.BookArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/SearchService/findBooksByPartAuthorNameRequest", output = "http://ws.stroganov.org/SearchService/findBooksByPartAuthorNameResponse")
    public BookArray findBooksByPartAuthorName(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0);

    /**
     * @param arg1
     * @param arg0
     * @return returns org.stroganov.ws.BookArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/SearchService/findBooksByYearsRangeRequest", output = "http://ws.stroganov.org/SearchService/findBooksByYearsRangeResponse")
    public BookArray findBooksByYearsRange(
            @WebParam(name = "arg0", partName = "arg0")
                    int arg0,
            @WebParam(name = "arg1", partName = "arg1")
                    int arg1);

    /**
     * @param arg2
     * @param arg1
     * @param arg0
     * @return returns org.stroganov.ws.BookArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/SearchService/findBooksByParametersRequest", output = "http://ws.stroganov.org/SearchService/findBooksByParametersResponse")
    public BookArray findBooksByParameters(
            @WebParam(name = "arg0", partName = "arg0")
                    int arg0,
            @WebParam(name = "arg1", partName = "arg1")
                    int arg1,
            @WebParam(name = "arg2", partName = "arg2")
                    String arg2);

    /**
     * @param arg0
     * @return returns org.stroganov.ws.BookArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/SearchService/findBooksWithUserBookMarksRequest", output = "http://ws.stroganov.org/SearchService/findBooksWithUserBookMarksResponse")
    public BookArray findBooksWithUserBookMarks(
            @WebParam(name = "arg0", partName = "arg0")
                    User arg0);

}
