package org.stroganov.servlets.commands;

import org.stroganov.servlets.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) {
        return null;
    }
}
