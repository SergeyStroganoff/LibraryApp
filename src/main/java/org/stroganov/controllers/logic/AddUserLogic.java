package org.stroganov.controllers.logic;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;
import org.stroganov.util.MessageManager;

import java.io.IOException;

public class AddUserLogic {
    public static final String ERROR_SAVE_MESSAGE = "Ошибка при сохранении нового пользователя";
    private final Logger logger = Logger.getLogger(AddUserLogic.class);

    public String addNewUser(String userFullName, String userLogin, String password, String adminStatus) {
        int numberID = 0;
        boolean isAdmin = Boolean.TRUE.toString().equalsIgnoreCase(adminStatus);
        User user = new User(numberID, userFullName, userLogin, password, false, isAdmin);
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        try {
            if (!libraryDAO.addUser(user)) {
                return MessageManager.getProperty("message.addUserMessage.userExist");
            }
        } catch (IOException e) {
            logger.error(ERROR_SAVE_MESSAGE + e.getMessage());
            return MessageManager.getProperty("message.addUserMessage.errorIO") + e.getMessage();
        }
        return MessageManager.getProperty("message.addUserMessage.success");
    }
}
