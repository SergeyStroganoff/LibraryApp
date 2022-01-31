package org.stroganov.dialogue;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.ConsoleInterfaceException;
import org.stroganov.gui.UserInterface;

public class BookMarkGetterDialogue {
    public static final String ENTER_BOOK_S_ISBN_MESSAGE = "Enter book's ISBN for bookmark";
    public static final String ENTER_PAGE_NUMBER_MESSAGE = "Enter page number for bookMark";
    public static final String NOT_A_NUMBER_OF_PAGE_MESSAGE = "You entered not a number of page";
    public static final String NO_SUCH_PAGE_MESSAGE = "Book doesn't have such page";
    public static final String NO_SUCH_BOOK_MESSAGE = "No such book founded";
    private final Logger logger = Logger.getLogger(BookMarkGetterDialogue.class);

    public BookMark getBookMarkFromUser(UserInterface userInterface, LibraryDAO libraryDAO, User user) {
        BookMark bookMark = null;
        userInterface.showMessage(ENTER_BOOK_S_ISBN_MESSAGE);
        String numberISBN = userInterface.getStringFromUser();
        userInterface.showMessage(ENTER_PAGE_NUMBER_MESSAGE);
        int pageNumber;
        try {
            pageNumber = userInterface.getIntFromUser();
        } catch (ConsoleInterfaceException e) {
            logger.error(e.getMessage());
            userInterface.showMessage(NOT_A_NUMBER_OF_PAGE_MESSAGE);
            return null;
        }
        Book book = libraryDAO.findBook(numberISBN);
        if (book != null) {
            if (book.getPagesNumber() < pageNumber) {
                userInterface.showMessage(NO_SUCH_PAGE_MESSAGE);
                return null;
            }
            bookMark = new BookMark(book, user, pageNumber);
        } else {
            userInterface.showMessage(NO_SUCH_BOOK_MESSAGE);
        }
        return bookMark;
    }
}
