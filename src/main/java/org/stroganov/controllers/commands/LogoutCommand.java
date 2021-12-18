package org.stroganov.controllers.commands;


import org.stroganov.util.ConfigurationManager;
import org.stroganov.controllers.actions.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) {
        String page = ConfigurationManager.getProperties("path.page.index");
        // уничтожение сессии
        servletRequest.getSession().invalidate();
        return page;
    }
}
