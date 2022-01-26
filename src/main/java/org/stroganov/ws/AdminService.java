package org.stroganov.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.stroganov.entities.History;
import org.stroganov.entities.User;

import java.io.IOException;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface AdminService {

    @WebMethod(operationName = "addUser")
    boolean addUser(User user) throws IOException;

    @WebMethod(operationName = "deleteUser")
    boolean deleteUser(User user) throws IOException;

    @WebMethod(operationName = "blockUser")
    boolean blockUser(User user) throws IOException;

    @WebMethod(operationName = "unblockUser")
    boolean unblockUser(User user) throws IOException;

    @WebMethod(operationName = "getAllHistory")
    History[] getAllHistory();
}
