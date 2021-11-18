package org.stroganov.dialogue;

import org.stroganov.entities.User;
import org.stroganov.gui.UserInterface;
import org.stroganov.util.PasswordAuthentication;

public class UserGetterDialogue {

    public static final String ENTER_FULL_NAME_MESSAGE = "Enter full user's name";
    public static final String ENTER_USER_LOGIN = "Enter user login";
    public static final String ENTER_USER_PASSWORD = "Enter user password";
    public static final String BE_ADMIN_MESSAGE = "Will user be admin?";

    public User getUserFromDialogue(UserInterface userInterface) {
        int numberID = 0;
        userInterface.showMessage(ENTER_FULL_NAME_MESSAGE);
        String fullName = userInterface.getStringFromUser();
        userInterface.showMessage(ENTER_USER_LOGIN);
        String login = userInterface.getStringFromUser();
        userInterface.showMessage(ENTER_USER_PASSWORD);
        String password = userInterface.getStringFromUser();
        String passcodeHash = PasswordAuthentication.hash(password.toCharArray());
        userInterface.showMessage(BE_ADMIN_MESSAGE);
        String isAdminString = userInterface.getStringFromUser();
        boolean isAdmin = isAdminString.equalsIgnoreCase("yes") || isAdminString.equalsIgnoreCase("y");
        return new User(numberID, fullName, login, passcodeHash, false, isAdmin);
    }
}
