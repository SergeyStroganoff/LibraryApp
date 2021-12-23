package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest servletRequest, HttpServletResponse response) {
        return ConfigurationManager.getProperties("path.page.empty-page");
    }
}
