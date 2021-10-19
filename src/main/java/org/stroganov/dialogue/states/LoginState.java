package org.stroganov.dialogue.states;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginState extends State {


    @Override
    public void showStateMenu() {
        userInterface.showMessage("Введите свой логин и пароль");
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public List<String> getUserAnswerString() {
        String login = userInterface.getStringFromUser();
        String password = userInterface.getStringFromUser();
        return new ArrayList<>(Arrays.asList(login, password));
    }

    @Override
    public void doStateAction() {
    }
}
