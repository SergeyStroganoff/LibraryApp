package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;

public class CookeCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest servletRequest) {
        String userLogin = CookieUtil.getUserFromCookie(servletRequest);
        if (userLogin != null) {
            servletRequest.getSession().setAttribute("userLogin", userLogin);
            return ConfigurationManager.getProperties("path.page.main");
        }
        return ConfigurationManager.getProperties("path.page.login");
    }
}
