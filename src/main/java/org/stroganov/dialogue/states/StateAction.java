package org.stroganov.dialogue.states;

import java.util.List;

/**
 * Общий интерфейс всех состояний.
 */

public interface StateAction {

    void showStateMenu();

    void showMessage(String message);

    List<String> getUserAnswerString();

    void doStateAction();
}
