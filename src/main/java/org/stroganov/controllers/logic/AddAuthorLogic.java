package org.stroganov.controllers.logic;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Author;
import org.stroganov.util.DataManager;
import org.stroganov.util.MessageManager;

import java.io.IOException;

public class AddAuthorLogic {
    private final Logger logger = Logger.getLogger(AddAuthorLogic.class);

    public String addNewAuthor(String authorName) {
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        Author author = new Author(1, authorName);
        try {
            if (!libraryDAO.addAuthor(author)) {
                return MessageManager.getProperty("message.addNewAuthorMessage.authorExist");
            }
        } catch (IOException e) {
            logger.error("Ошибка при сохранении нового автора" + e.getMessage());
            return MessageManager.getProperty("message.addNewAuthorMessage.errorIO") + e.getMessage();
        }
        return MessageManager.getProperty("message.addNewAuthorMessage.success");
    }

}
