package org.stroganov.ws;


import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.ArrayList;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface LibraryService {
    @WebMethod(operationName = "getSimple")
    String getHelloWorldAsString(String name);

    @WebMethod(operationName = "getHelloList")
    ArrayList<String> returnHelloList();
}
