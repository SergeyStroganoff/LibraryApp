package org.stroganov.dialogue.states;

import org.stroganov.exceptions.UnrealizedFunctionalityException;
import org.stroganov.gui.UserInterface;
import org.stroganov.gui.UserInterfaceFactory;

public abstract class State implements StateAction {

    /**
     * Контекст передаёт себя в конструктор состояния, чтобы состояние могло
     * обращаться к его данным и методам в будущем, если потребуется.
     */

    UserInterface userInterface;

    public State() {
        try {
            userInterface = UserInterfaceFactory.getUserInterface("UserConsoleInterface");
        } catch (UnrealizedFunctionalityException e) {
            e.printStackTrace();
        }
    }
}
