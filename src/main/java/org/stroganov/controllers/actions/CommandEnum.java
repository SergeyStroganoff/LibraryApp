package org.stroganov.controllers.actions;

import org.stroganov.controllers.commands.*;
import org.stroganov.controllers.commands.AddUserCommand;
import org.stroganov.controllers.commands.DeleteBookCommand;

public enum CommandEnum {
    LOGIN(new LoginCommand()), LOGOUT(new LogoutCommand()), COOKIE(new CookeCommand()),
    ADD_BOOK(new AddNewBookCommand()), ADD_BOOK_MARK(new AddBookMarkCommand()), ADD_AUTHOR(new AddAuthorCommand()),
    ADD_USER(new AddUserCommand()), DELETE_BOOK(new DeleteBookCommand()), DELETE_BOOK_MARK(new DeleteBookMarkCommand()),
    DELETE_AUTHOR(new DeleteAuthorCommand()), BLOCK_USER(new BlockUserCommand()), UNBLOCK_USER(new UnblockUserCommand());

    ActionCommand actionCommand;

    CommandEnum(ActionCommand actionCommand) {
        this.actionCommand = actionCommand;
    }

    public ActionCommand getActionCommand() {
        return actionCommand;
    }
}


