package org.stroganov.gui;

import java.io.BufferedReader;
import java.io.IOException;

public class UserConsoleInterface implements UserInterface {
    BufferedReader reader;

    public UserConsoleInterface(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String getStringFromUser() {
        String expressionString = " ";
        try {
            expressionString = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expressionString;
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
