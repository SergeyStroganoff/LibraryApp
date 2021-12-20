package org.stroganov.controllers.commands;


import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.LoginLogic;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest servletRequest) {
        String page = null;
// извлечение из запроса логина и пароля
        String login = servletRequest.getParameter(PARAM_NAME_LOGIN);
        String pass = servletRequest.getParameter(PARAM_NAME_PASSWORD);
// проверка логина и пароля
        LoginLogic loginLogic = new LoginLogic();
        if (loginLogic.checkLogin(login, pass)) {
            servletRequest.setAttribute("user", login);
// определение пути к main.jsp
            page = ConfigurationManager.getProperties("path.page.main");
        } else {
            servletRequest.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperties("path.page.login");
        }
        return page;
    }
}




