package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.history.WEBHistoryManager;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.DataManager;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUserBooksCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.showBooksList");
        String currentUserLogin = (String) request.getSession().getAttribute("userLogin");
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        User user = libraryDAO.findUser(currentUserLogin);
        List<Book> userBooksList = libraryDAO.findBooksWithUserBookMarks(user);
        request.setAttribute("books", userBooksList);
        WEBHistoryManager.saveAction(currentUserLogin, "User: " + currentUserLogin
                + " " + MessageManager.getProperty("message.showUserBooks.success"));
        return page;
    }
}
