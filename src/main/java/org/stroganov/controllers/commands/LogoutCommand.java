package org.stroganov.controllers.commands;


import org.apache.log4j.Logger;
import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.history.WEBHistoryManager;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements ActionCommand {
    private final Logger logger = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest servletRequest, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.login");
        // уничтожение сессии
        WEBHistoryManager.saveAction((String) servletRequest.getSession().getAttribute("userLogin"), MessageManager.getProperty("message.history.logout"));
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        servletRequest.getSession().invalidate();
        return page;
    }
}
