package org.stroganov.dialogue;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.ConsoleInterfaceException;
import org.stroganov.gui.UserInterface;

public class BookMarkGetterDialogue {
    Logger logger = Logger.getLogger(BookMarkGetterDialogue.class);

    public BookMark getBookMarkFromUser(UserInterface userInterface, LibraryDAO libraryDAO, User user) {
        BookMark bookMark = null;
        userInterface.showMessage("Enter book's ISBN for bookmark");
        String numberISBN = userInterface.getStringFromUser();
        userInterface.showMessage("Enter page number for bookMark");
        int pageNumber;
        try {
            pageNumber = userInterface.getIntFromUser();
        } catch (ConsoleInterfaceException e) {
            logger.error(e.getMessage());
            userInterface.showMessage("You entered not a number of page");
            return null;
        }
        Book book = libraryDAO.findBook(numberISBN);
        if (book != null) {
            if (book.getPagesNumber() < pageNumber) {
                userInterface.showMessage("Book doesn't have such page");
                return null;
            }
            bookMark = new BookMark(book, user, pageNumber);
        } else {
            userInterface.showMessage("No such book founded");
        }
        return bookMark;
    }
}
