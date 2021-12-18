package org.stroganov.controllers.actions;

import org.stroganov.controllers.commands.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private ActionFactory() {
    }

    public static ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            current = CommandEnum.valueOf(action.toUpperCase()).getActionCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + "- Not defined");
        }
        return current;
    }
}
