package org.stroganov.dialogue;

import org.stroganov.dialogue.states.StateAction;

public class UserDialogue {
    private StateAction stateAction;



    public StateAction getStateAction() {
        return stateAction;
    }

    public void setStateAction(StateAction stateAction) {
        this.stateAction = stateAction;
    }

}
