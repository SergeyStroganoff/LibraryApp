package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.History;
import org.stroganov.history.WEBHistoryManager;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.DataManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowHistoryCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.showHistory");
        String currentUserLogin = (String) request.getSession().getAttribute("userLogin");
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        List<History> historyAllUsersList = libraryDAO.getAllHistory();
        request.setAttribute("history", historyAllUsersList);
        WEBHistoryManager.saveAction(currentUserLogin, "User: " + currentUserLogin + " queried history all users");
        return page;
    }
}
