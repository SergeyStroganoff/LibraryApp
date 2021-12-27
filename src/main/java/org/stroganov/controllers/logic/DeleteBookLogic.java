package org.stroganov.controllers.logic;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.util.DataManager;
import org.stroganov.util.MessageManager;

import java.io.IOException;

public class DeleteBookLogic {
    private final Logger logger = Logger.getLogger(DeleteBookLogic.class);

    public String deleteBook(String bookISBN) {
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        Book book = libraryDAO.findBook(bookISBN);
        if (book == null) {
            return MessageManager.getProperty("message.deleteBook.noSuchBook");
        }
        String message;
        try {
            if (libraryDAO.deleteBook(book)) {
                message = MessageManager.getProperty("message.deleteBook.success");
            } else {
                message = MessageManager.getProperty("message.deleteBook.deletingError");
            }
        } catch (IOException e) {
            logger.error(message = MessageManager.getProperty("message.deleteBook.IOError") + " " + e.getMessage());
        }
        return message;
    }
}
