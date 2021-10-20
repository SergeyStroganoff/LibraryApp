package org.stroganov.dialogue;

import org.apache.log4j.Logger;
import org.stroganov.dao.DAOFactory;
import org.stroganov.dao.DAOType;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.exceptions.UnrealizedFunctionalityException;

public class UserDialogueManager {

    private LibraryDAO libraryDAO;
    private UserDialogueManager userDialogueManager;
    private Logger logger = Logger.getLogger(UserDialogueManager.class);


    public UserDialogueManager(UserDialogueManager userDialogueManager) {
        this.userDialogueManager = userDialogueManager;
        {
                libraryDAO = DAOFactory.getLibraryDAO(DAOType.JSON);
        }
    }


    public void runService() throws UnrealizedFunctionalityException {

    }
}
