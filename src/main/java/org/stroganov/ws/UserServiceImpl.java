package org.stroganov.ws;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Author;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.History;
import org.stroganov.util.DataManager;

import java.io.IOException;

@HandlerChain(file = "../userHandler.xml")
@WebService(endpointInterface = "org.stroganov.ws.UserService")
public class UserServiceImpl implements UserService {

    private final LibraryDAO libraryDAO = DataManager.getLibraryDAO();

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) throws IOException {
        return libraryDAO.deleteAuthorWithAllHisBooks(author);
    }

    @Override
    public boolean addHistoryEvent(History history) {
        return libraryDAO.addHistoryEvent(history);
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) throws IOException {
        return libraryDAO.deleteBookMark(bookMark);
    }

    @Override
    public boolean addBookMark(BookMark bookMark) throws IOException {
        return libraryDAO.addBookMark(bookMark);
    }
}

