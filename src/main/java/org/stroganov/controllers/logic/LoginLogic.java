package org.stroganov.controllers.logic;

import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;
import org.stroganov.util.PasswordAuthentication;

public class LoginLogic {
    public boolean checkLogin(String login, String password) {
        if ("".equals(login) && "".equals(password)) {
            return false;
        }
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        User user = libraryDAO.findUser(login);
        if (user != null) {
            return PasswordAuthentication.authenticate(password.toCharArray(), user.getPasscodeHash());
        } else {
            return false;
        }
    }

}
