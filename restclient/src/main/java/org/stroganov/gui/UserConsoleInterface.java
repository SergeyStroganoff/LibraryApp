package org.stroganov.gui;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stroganov.App;
import org.stroganov.exceptions.ConsoleInterfaceException;

import java.io.BufferedReader;
import java.io.IOException;

public class UserConsoleInterface implements UserInterface {

    private static final Logger logger = Logger.getLogger(App.class);
    final BufferedReader reader;
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
    public int getIntFromUser() throws ConsoleInterfaceException {
        String userString = getStringFromUser();
        if (userString.matches("^\\d{1,10}")) {
            return Integer.parseInt(userString);
        } else throw new ConsoleInterfaceException("Got incorrect format number from user");

    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
