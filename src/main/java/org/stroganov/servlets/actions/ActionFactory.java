package org.stroganov.servlets.actions;

import org.stroganov.servlets.commands.EmptyCommand;
import org.stroganov.servlets.commands.LoginCommand;
import org.stroganov.servlets.commands.LogoutCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public static ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnumCommand = CommandEnum.valueOf(action.toUpperCase());

            switch (currentEnumCommand) {
                case LOGIN:
                    current = new LoginCommand();
                    break;
                case LOGOUT:
                    current = new LogoutCommand();
                    break;
                default:
                    request.setAttribute("wrongAction", action + "- Not defined");
            }

        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + "- Not defined");
        }
        return current;
    }
}
