package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.User;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.CookieUtil;
import org.stroganov.util.DataManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookeCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest servletRequest, HttpServletResponse response) {
        String userLogin = CookieUtil.getUserFromCookie(servletRequest);
        if (userLogin != null) {
            servletRequest.getSession().setAttribute("userLogin", userLogin);
            LibraryDAO libraryDAO = DataManager.getLibraryDAO();
            User user = libraryDAO.findUser(userLogin);
            String userRole = user.isAdmin() ? "admin" : "user";
            servletRequest.getSession().setAttribute("role", userRole);

            return ConfigurationManager.getProperties("path.page.main");
        }
        return ConfigurationManager.getProperties("path.page.login");
    }
}
