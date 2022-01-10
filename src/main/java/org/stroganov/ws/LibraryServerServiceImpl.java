package org.stroganov.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.ResponseWrapper;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Author;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;

import java.util.ArrayList;

@WebService(endpointInterface = "org.stroganov.ws.LibraryService")
public class LibraryServerServiceImpl implements org.stroganov.ws.LibraryService {

    LibraryDAO libraryDAO = DataManager.getLibraryDAO();

    @Override
    @WebMethod(operationName = "getSimple")
    public String getHelloWorldAsString(String name) {
        return name;
    }

    @Override
    @WebMethod(operationName = "getHelloList")
    @ResponseWrapper(className = "java.util.ArrayList")
    public ArrayList<String> returnHelloList() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("Hello - I'm Library Service");
        return stringArrayList;
    }

    @Override
    public Author getNewAuthor(String name) {
        return new Author(1, name);
    }

    @Override
    public User[] getNewUser(@WebParam(name = "userName") String name) {
        ArrayList<User> stringArrayListUsers = new ArrayList<>();
        User user = new User(1, name, "admin", "sdf34rwf", false, true);
        User userNext = new User(1, "Иван", "admin", "sdf34rwf", false, true);
        stringArrayListUsers.add(user);
        stringArrayListUsers.add(userNext);
        return stringArrayListUsers.toArray(new User[0]);
    }
}
