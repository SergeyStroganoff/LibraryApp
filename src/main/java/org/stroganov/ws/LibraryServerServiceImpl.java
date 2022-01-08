package org.stroganov.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.ArrayList;

@WebService(endpointInterface = "org.stroganov.ws.LibraryService")
public class LibraryServerServiceImpl implements org.stroganov.ws.LibraryService {

    @Override
    @WebMethod(operationName = "getSimple")
    public String getHelloWorldAsString(String name) {
        return name;
    }

    @Override
    @WebMethod(operationName = "getHelloList")
    public ArrayList<String> returnHelloList() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("Hello - Im Library Service");
        return stringArrayList;
    }
}
