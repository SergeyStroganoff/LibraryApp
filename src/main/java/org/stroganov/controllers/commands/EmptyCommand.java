package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) {
        return null;
    }
}
