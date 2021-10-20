package org.stroganov.dialogue;

import org.apache.log4j.Logger;
import org.stroganov.dao.DAOFactory;
import org.stroganov.dao.DAOType;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.User;
import org.stroganov.gui.UserInterface;
import org.stroganov.history.HistoryManager;

public class UserDialogueManager {

    private Logger logger = Logger.getLogger(UserDialogueManager.class);
    private LibraryDAO libraryDAO;
    private HistoryManager historyManager;
    private UserInterface userInterface;

    public UserDialogueManager(HistoryManager historyManager, UserInterface userInterface) {
        this.historyManager = historyManager;
        this.userInterface = userInterface;
        libraryDAO = DAOFactory.getLibraryDAO(DAOType.JSON);
    }

    public void runDialogue() {
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
                if ()
            }

        } while ();


    }
}
