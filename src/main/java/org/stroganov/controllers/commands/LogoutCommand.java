package org.stroganov.controllers.commands;


import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.history.WEBHistoryManager;
import org.stroganov.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements ActionCommand {

    public static final String USER_HAVE_EXITED = "User have exited";

    @Override
    public String execute(HttpServletRequest servletRequest, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.login");
        // уничтожение сессии
        WEBHistoryManager.saveAction((String) servletRequest.getSession().getAttribute("userLogin"), USER_HAVE_EXITED);
        servletRequest.getSession().invalidate();
        return page;
    }
}
