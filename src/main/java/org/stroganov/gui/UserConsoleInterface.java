package org.stroganov.gui;

import org.apache.log4j.Logger;
import org.stroganov.App;
import org.stroganov.exceptions.ConsoleInterfaceError;

import java.io.BufferedReader;
import java.io.IOException;

public class UserConsoleInterface implements UserInterface {

    private static final Logger logger = Logger.getLogger(App.class);
    BufferedReader reader;

    public UserConsoleInterface(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String getStringFromUser() {
        String expressionString = "";
        try {
            expressionString = reader.readLine();
        } catch (IOException e) {
            logger.error(e.getMessage());
            showMessage(e.getMessage());
        }
        return expressionString;
    }

    @Override
    public int getIntFromUser() throws ConsoleInterfaceError {
        String userString = getStringFromUser();
        if (userString.matches("^\\d{1,10}")) {
            return Integer.parseInt(userString);
        } else throw new ConsoleInterfaceError("Got incorrect format number from user");

    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
