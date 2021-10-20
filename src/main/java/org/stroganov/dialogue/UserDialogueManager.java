package org.stroganov.dialogue;

import org.apache.log4j.Logger;
import org.stroganov.dao.DAOFactory;
import org.stroganov.dao.DAOType;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.User;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.gui.UserInterface;
import org.stroganov.history.HistoryManager;
import org.stroganov.util.PasswordAuthentication;

import java.io.IOException;

public class UserDialogueManager {

    private final Logger logger = Logger.getLogger(UserDialogueManager.class);
    private LibraryDAO libraryDAO = null;
    private final HistoryManager historyManager;
    private final UserInterface userInterface;

    public UserDialogueManager(HistoryManager historyManager, UserInterface userInterface) {
        this.historyManager = historyManager;
        this.userInterface = userInterface;
        try {
            libraryDAO = DAOFactory.getLibraryDAO(DAOType.JSON);
        } catch (DBExceptions e) {
            userInterface.showMessage(e.getMessage());
            System.exit(1);
        }
    }

    public void runDialogue() {
        System.out.println("Run Started");
        User user = null;

        // ask login pass
        userInterface.showMessage("Input login and password, 'q' for exit");
        userInterface.showMessage("You have only 3 attempts");
        byte countAttempt = 3;
        do {
            countAttempt--;
            String login = userInterface.getStringFromUser();
            if ("q".equals(login)) {
                historyManager.saveAction("User asked exit before login");
                System.exit(1);
            }
            String password = userInterface.getStringFromUser();
            if (!"".equals(login) && !"".equals(password)) {
                user = libraryDAO.findUser(login);
            } else {
                userInterface.showMessage("You entered empty login, password? try again!");
            }
            if (user != null) {
                boolean passwordCheck = PasswordAuthentication.authenticate(password.toCharArray(), user.getPasscodeHash());
                if (!passwordCheck) {
                    userInterface.showMessage("You entered incorrect password, try again!");
                    historyManager.saveAction("User " + user.getLogin() + " tried to enter with incorrect password");
                    user = null;
                } else {
                    userInterface.showMessage("You entered incorrect login, try again!");
                }
            }
        } while (user == null && countAttempt > 0);

        if (user == null) {
            userInterface.showMessage("You entered incorrect credentials three times, \n program will be closed");
            historyManager.saveAction("User entered incorrect credentials three times, program closed");
        }


    }
}
