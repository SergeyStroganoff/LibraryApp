package org.stroganov.controllers.actions;

import org.stroganov.controllers.commands.CookeCommand;
import org.stroganov.controllers.commands.LoginCommand;
import org.stroganov.controllers.commands.LogoutCommand;

public enum CommandEnum {
    LOGIN(new LoginCommand()), LOGOUT(new LogoutCommand()), COOKIE(new CookeCommand());
    ActionCommand actionCommand;

    CommandEnum(ActionCommand actionCommand) {
        this.actionCommand = actionCommand;
    }

    public ActionCommand getActionCommand() {
        return actionCommand;
    }
}


