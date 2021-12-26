package org.stroganov.controllers.logic;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;
import org.stroganov.util.MessageManager;

import java.io.IOException;

public class AddBookMarkLogic {
    public static final String ERROR_WITH_BOOK_MARK_SAVE = "Error with bookMarkSave";
    private final Logger logger = Logger.getLogger(AddBookMarkLogic.class);

    public String addNewBookMark(String numberISBN, String userLogin, String bookPageNumber) {
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        Book book = libraryDAO.findBook(numberISBN);
        int pageNumber = Integer.parseInt(bookPageNumber);
        if (book != null) {
            if (book.getPagesNumber() < pageNumber) {
                return MessageManager.getProperty("message.addNewBookMarkMessage.pageError");
            }
            User user = libraryDAO.findUser(userLogin);
            BookMark bookMark = new BookMark(book, user, pageNumber);
            try {
                libraryDAO.addBookMark(bookMark);
            } catch (IOException e) {
                logger.error(ERROR_WITH_BOOK_MARK_SAVE, e);
                return MessageManager.getProperty("message.addNewBookMarkMessage.saveError") + e.getMessage();
            }
        } else {
            return MessageManager.getProperty("message.addNewBookMarkMessage.bookError");
        }
        return MessageManager.getProperty("message.addNewBookMarkMessage.success");
    }
}
