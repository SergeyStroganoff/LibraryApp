package org.stroganov.dialogue;

import org.stroganov.entities.Author;
import org.stroganov.gui.UserInterface;

public class AuthorGetterDialogue {
    public static final String ENTER_BOOK_S_AUTHOR_NAME = "Enter book's author name:";

    public Author getAuthorFromUser(UserInterface userInterface) {
        userInterface.showMessage(ENTER_BOOK_S_AUTHOR_NAME);
        String authorName = userInterface.getStringFromUser();
        return new Author(1, authorName);
    }
}
