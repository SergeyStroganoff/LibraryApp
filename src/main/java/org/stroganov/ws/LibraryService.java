package org.stroganov.ws;


import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.stroganov.dao.LibraryDAO;

import java.util.ArrayList;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
//@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface LibraryService extends LibraryDAO {

    @WebMethod(operationName = "getSimple")
    String getHelloWorldAsString(String name);

    @WebMethod(operationName = "getHelloList")
    ArrayList<String> returnHelloList();
}
