package org.stroganov.dialogue;

import org.stroganov.dao.DAOFactory;
import org.stroganov.dao.DAOType;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.dialogue.states.AdminMenuState;
import org.stroganov.dialogue.states.LoginState;
import org.stroganov.dialogue.states.StateAction;
import org.stroganov.dialogue.states.UserMenuState;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UnrealizedFunctionalityException;

public class UserDialogueManager {

    UserDialogue userDialogue;
    LibraryDAO libraryDAO;
    User user;
    private StateAction stateAction;

    {
        try {
            libraryDAO = DAOFactory.getLibraryDAO(DAOType.JSON);
        } catch (UnrealizedFunctionalityException e) {
            e.printStackTrace();
        }
    }

    public UserDialogueManager(UserDialogue userDialogue) {
        this.userDialogue = userDialogue;
    }

    public void runService() throws UnrealizedFunctionalityException {
        userDialogue.setStateAction(new LoginState());
        userDialogue.getStateAction().showStateMenu();
        userDialogue.getStateAction().doStateAction();
        userDialogue.setStateAction(new UserMenuState());
        userDialogue.setStateAction(new AdminMenuState());
    }
}
