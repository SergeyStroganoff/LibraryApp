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

public class InitialDialogue {

    public static final String EXIT_BEFORE_LOGIN_MESSAGE = "User asked exit before login";
    public static final String INPUT_LOGIN_MESSAGE = "Input login and password, 'q' for exit";
    public static final String ATTEMPTS_MESSAGE = "You have only 3 attempts";
    private final Logger logger = Logger.getLogger(InitialDialogue.class);
    private LibraryDAO libraryDAO = null;
    private HistoryManager historyManager;
    private final UserInterface userInterface;

    public InitialDialogue(UserInterface userInterface) {
        this.userInterface = userInterface;
        try {
            libraryDAO = DAOFactory.getLibraryDAO(DAOType.MYSQL);
        } catch (DBExceptions e) {
            userInterface.showMessage(e.getMessage());
            System.exit(1);
        }
        try {
            historyManager = new HistoryManager();
        } catch (
                IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void runDialogue() {
        User user = null;
        // ask login pass
        userInterface.showMessage(INPUT_LOGIN_MESSAGE);
        userInterface.showMessage(ATTEMPTS_MESSAGE);
        byte countAttempt = 3;
        do {
            countAttempt--;
            String login = userInterface.getStringFromUser();
            if ("q".equals(login)) {
                historyManager.saveAction(EXIT_BEFORE_LOGIN_MESSAGE);
                logger.info(EXIT_BEFORE_LOGIN_MESSAGE);
                System.exit(1);
            }
            String password = userInterface.getStringFromUser();
            if (!"".equals(login) && !"".equals(password)) {
                user = libraryDAO.findUser(login);
            }
            if (user != null) {
                boolean passwordCheck = PasswordAuthentication.authenticate(password.toCharArray(), user.getPasscodeHash());
                if (!passwordCheck) {
                    userInterface.showMessage("You entered incorrect password, try again!");
                    historyManager.saveAction("User " + user.getLogin() + " tried to enter with incorrect password");
                    user = null;
                }
            } else {
                userInterface.showMessage("You entered incorrect login, try again!");
            }
        } while (user == null && countAttempt > 0);

        if (user == null) {
            userInterface.showMessage("You entered incorrect credentials three times,\nprogram will be closed");
            System.exit(1);
        }
        historyManager.saveAction("User " + user.getLogin() + " entered in system");
        logger.info("User " + user.getLogin() + " entered in system");
        MenuManagerDialogue menuManagerDialogue = new MenuManagerDialogue(libraryDAO, historyManager, userInterface, user);
        menuManagerDialogue.runDialogue();
    }
}
