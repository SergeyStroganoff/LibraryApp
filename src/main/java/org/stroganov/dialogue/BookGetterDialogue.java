package org.stroganov.dialogue;

import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.gui.UserInterface;

public class BookGetterDialogue {

    public static final String ENTER_BOOK_S_ISBN = "Enter book's ISBN:";
    public static final String ENTER_BOOK_S_NAME = "Enter book's name:";

    public static final String ENTER_BOOK_S_PUBLISHING_YEAR = "Enter book's publishing year:";
    public static final String ENTER_BOOK_S_PAGES_COUNT = "Enter book's pages count:";
    private final AuthorGetterDialogue authorGetterDialogue = new AuthorGetterDialogue();

    public Book getBookFromUser(UserInterface userInterface) {
        userInterface.showMessage(ENTER_BOOK_S_ISBN);
        String numberISBN = userInterface.getStringFromUser();
        userInterface.showMessage(ENTER_BOOK_S_NAME);
        String name = userInterface.getStringFromUser();
        Author author = authorGetterDialogue.getAuthorFromUser(userInterface);
        userInterface.showMessage(ENTER_BOOK_S_PUBLISHING_YEAR);
        int yearPublishing = Integer.parseInt(userInterface.getStringFromUser());
        userInterface.showMessage(ENTER_BOOK_S_PAGES_COUNT);
        int pagesCount = Integer.parseInt(userInterface.getStringFromUser());
        return new Book(numberISBN, name, author, yearPublishing, pagesCount);
    }

    public AuthorGetterDialogue getAuthorGetterDialogue() {
        return authorGetterDialogue;
    }
}
