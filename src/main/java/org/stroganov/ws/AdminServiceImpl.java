
package org.stroganov.ws;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.entities.History;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;
import org.stroganov.wsClient.AdminServiceClient;

import java.io.IOException;

@HandlerChain(file = "../adminHandler.xml")
@WebService(endpointInterface = "org.stroganov.ws.AdminService")
public class AdminServiceImpl implements AdminService {

    private final LibraryDAO libraryDAO = DataManager.getLibraryDAO();

    public boolean deleteBook(Book book) throws IOException {
        return libraryDAO.deleteBook(book);
    }

    @Override
    public boolean addUser(User user) throws IOException {
        return libraryDAO.addUser(user);
    }

    @Override
    public boolean deleteUser(User user) throws IOException {
        return libraryDAO.deleteUser(user);
    }

    @Override
    public boolean blockUser(User user) throws IOException {
        return libraryDAO.blockUser(user);
    }

    @Override
    public boolean unblockUser(User user) throws IOException {
        return libraryDAO.unblockUser(user);
    }

    @Override
    public History[] getAllHistory() {
        return libraryDAO.getAllHistory().toArray(new History[0]);
    }
}
