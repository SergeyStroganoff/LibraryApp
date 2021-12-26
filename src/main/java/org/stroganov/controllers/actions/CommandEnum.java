package org.stroganov.controllers.actions;

import org.stroganov.controllers.commands.*;

public enum CommandEnum {
    LOGIN(new LoginCommand()), LOGOUT(new LogoutCommand()), COOKIE(new CookeCommand()),
    ADD_BOOK(new AddNewBookCommand()), ADD_BOOK_MARK(new AddBookMarkCommand()), ADD_AUTHOR(new AddAuthorCommand());

    ActionCommand actionCommand;

    CommandEnum(ActionCommand actionCommand) {
        this.actionCommand = actionCommand;
    }

    public ActionCommand getActionCommand() {
        return actionCommand;
    }
}


