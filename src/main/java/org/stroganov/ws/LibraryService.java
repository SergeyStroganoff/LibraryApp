package org.stroganov.ws;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.ResponseWrapper;
import org.stroganov.entities.Author;
import org.stroganov.entities.User;

import java.util.ArrayList;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
//@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface LibraryService {

    @WebMethod(operationName = "getSimple")
    String getHelloWorldAsString(String name);

    @WebMethod(operationName = "getHelloList")
    ArrayList<String> returnHelloList();

    @WebMethod(operationName = "getNewAuthor")
    @ResponseWrapper(className = "org.stroganov.entities.Author")
    Author getNewAuthor(@WebParam(name = "name") String name);

    @WebMethod(operationName = "getNewUser")
    User[] getNewUser(@WebParam(name = "userName") String name);
}
