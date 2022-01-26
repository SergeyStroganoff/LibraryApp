
package org.stroganov.wsClient;

import jakarta.jws.*;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;
import org.stroganov.entities.*;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebService(name = "BookService", targetNamespace = "http://ws.stroganov.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
        ObjectFactory.class
})
public interface BookServiceClient {


    /**
     * @param arg0
     * @return returns boolean
     * @throws org.stroganov.ws.IOException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/BookService/deleteBookRequest", output = "http://ws.stroganov.org/BookService/deleteBookResponse", fault = {
            @FaultAction(className = IOException_Exception.class, value = "http://ws.stroganov.org/BookService/deleteBook/Fault/IOException")
    })
    public boolean deleteBook(
            @WebParam(name = "arg0", partName = "arg0")
                    Book arg0)
            throws IOException_Exception
    ;

    /**
     * @param arg0
     * @return returns boolean
     * @throws org.stroganov.ws.IOException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/BookService/addBookRequest", output = "http://ws.stroganov.org/BookService/addBookResponse", fault = {
            @FaultAction(className = IOException_Exception.class, value = "http://ws.stroganov.org/BookService/addBook/Fault/IOException")
    })
    public boolean addBook(
            @WebParam(name = "arg0", partName = "arg0")
                    Book arg0)
            throws IOException_Exception
    ;

    /**
     * @param arg0
     * @return returns boolean
     * @throws org.stroganov.ws.IOException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/BookService/addBookLIstRequest", output = "http://ws.stroganov.org/BookService/addBookLIstResponse", fault = {
            @FaultAction(className = IOException_Exception.class, value = "http://ws.stroganov.org/BookService/addBookLIst/Fault/IOException")
    })
    public boolean addBookLIst(
            @WebParam(name = "arg0", partName = "arg0")
                    BookArray arg0)
            throws IOException_Exception
    ;

    /**
     * @param arg0
     * @return returns boolean
     * @throws org.stroganov.ws.IOException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.stroganov.org/BookService/addAuthorRequest", output = "http://ws.stroganov.org/BookService/addAuthorResponse", fault = {
            @FaultAction(className = IOException_Exception.class, value = "http://ws.stroganov.org/BookService/addAuthor/Fault/IOException")
    })
    public boolean addAuthor(
            @WebParam(name = "arg0", partName = "arg0")
                    Author arg0)
            throws IOException_Exception
    ;

}