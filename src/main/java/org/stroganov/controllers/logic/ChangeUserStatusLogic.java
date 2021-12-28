package org.stroganov.controllers.logic;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;
import org.stroganov.util.MessageManager;

import java.io.IOException;

public class ChangeUserStatusLogic {
    private final Logger logger = Logger.getLogger(ChangeUserStatusLogic.class);

    public String changeUserStatus(String userLogin, String userStatus) {
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        User user = libraryDAO.findUser(userLogin);
        if (user == null) {
            return MessageManager.getProperty("message.changeUserStatusUser.noUser");
        }
        String message = MessageManager.getProperty("message.changeUserStatusUser.success");
        try {
            if (userStatus.equals("block")) {
                libraryDAO.blockUser(user);
            } else {
                libraryDAO.unblockUser(user);
            }
        } catch (IOException e) {
            message = MessageManager.getProperty("message.changeUserStatusUser.error") + " " + e.getMessage();
            logger.error(message);
        }
        return message;
    }
}
