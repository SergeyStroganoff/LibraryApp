package org.stroganov.controllers.commands;


import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.LoginLogic;
import org.stroganov.entities.User;
import org.stroganov.history.WEBHistoryManager;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.CookieUtil;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest servletRequest, HttpServletResponse response) {
        String page;
// извлечение из запроса логина и пароля
        String login = servletRequest.getParameter(PARAM_NAME_LOGIN);
        String pass = servletRequest.getParameter(PARAM_NAME_PASSWORD);
// проверка логина и пароля
        LoginLogic loginLogic = new LoginLogic();
        User user = loginLogic.getUserByLoginPassword(login, pass);
        if (user != null) {
            HttpSession session = servletRequest.getSession(true);
            CookieUtil.setCookie(response, user.getLogin());
            if (session.getAttribute("role") == null) {
                String userRole = user.isAdmin() ? "admin" : "user";
                session.setAttribute("role", userRole);
            }
            session.setAttribute("userLogin", user.getLogin());
            page = ConfigurationManager.getProperties("path.page.main");
            WEBHistoryManager.saveAction(user.getLogin(), MessageManager.getProperty("message.history.login"));
        } else {
            servletRequest.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperties("path.page.login");
        }
        return page;
    }
}




