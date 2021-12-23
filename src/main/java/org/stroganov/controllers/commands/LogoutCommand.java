package org.stroganov.controllers.commands;


import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest servletRequest, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.login");
        // уничтожение сессии
        servletRequest.getSession().invalidate();
        return page;
    }
}
