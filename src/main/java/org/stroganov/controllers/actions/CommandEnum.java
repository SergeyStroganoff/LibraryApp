package org.stroganov.controllers.actions;

import org.stroganov.controllers.commands.*;

public enum CommandEnum {
    LOGIN(new LoginCommand()), LOGOUT(new LogoutCommand()), COOKIE(new CookeCommand()),
    ADD_BOOK(new AddNewBookCommand()), ADD_BOOK_MARK(new AddBookMarkCommand()), ADD_AUTHOR(new AddAuthorCommand()),
    ADD_USER(new AddUserCommand()), DELETE_BOOK(new DeleteBookCommand()), DELETE_BOOK_MARK(new DeleteBookMarkCommand()),
    DELETE_AUTHOR(new DeleteAuthorCommand()), BLOCK_USER(new BlockUserCommand()), UNBLOCK_USER(new UnblockUserCommand()),
    SHOW_HISTORY(new ShowHistoryCommand()), SHOW_MY_BOOK(new ShowUserBooksCommand()), SEARCH_BOOK(new SearchBookCommand());

    private final ActionCommand actionCommand;

    CommandEnum(ActionCommand actionCommand) {
        this.actionCommand = actionCommand;
    }

    public ActionCommand getActionCommand() {
        return actionCommand;
    }
}


