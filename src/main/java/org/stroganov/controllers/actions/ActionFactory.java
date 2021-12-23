package org.stroganov.controllers.actions;

import org.apache.log4j.Logger;
import org.stroganov.controllers.commands.EmptyCommand;
import org.stroganov.util.FileUtil;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final Logger logger = Logger.getLogger(ActionFactory.class);
    private ActionFactory() {
    }

    public static ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        logger.debug("got command string " + action);
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
