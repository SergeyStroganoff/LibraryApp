package org.stroganov.controllers.logic;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Author;
import org.stroganov.util.DataManager;
import org.stroganov.util.MessageManager;

import java.io.IOException;

public class DeleteAuthorLogic {
    private final Logger logger = Logger.getLogger(DeleteAuthorLogic.class);

    public String deleteAuthor(String authorName) {
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        Author author = libraryDAO.findAuthor(authorName);
        if (author == null) {
            return MessageManager.getProperty("message.deleteAuthor.noSuchAuthor");
        }
        String message = MessageManager.getProperty("message.deleteAuthor.success");
        try {
            libraryDAO.deleteAuthorWithAllHisBooks(author);
        } catch (IOException e) {
            message = MessageManager.getProperty("message.deleteAuthor.error") + " " + e.getMessage();
            logger.error(message);
        }
        return message;
    }

}
