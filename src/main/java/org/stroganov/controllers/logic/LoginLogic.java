package org.stroganov.controllers.logic;

import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;
import org.stroganov.util.PasswordAuthentication;

public class LoginLogic {
    public User getUserByLoginPassword(String login, String password) {
        if ("".equals(login) && "".equals(password)) {
            return null;
        }
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        User user = libraryDAO.findUser(login);
        if (user != null && PasswordAuthentication.authenticate(password.toCharArray(), user.getPasscodeHash())) {
            return user;
        }
        return null;
    }
}
