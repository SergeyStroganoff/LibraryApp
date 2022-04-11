package org.stroganov.gui;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class UserInterfaceFactory {

    private static final String REALIZATION_EXCEPTION_MESSAGE = "SwingInterface - не реализовано";
    private static final String ERROR_PARAMETER_MESSAGE = "Тип интерфейса указан неверно";

    private UserInterfaceFactory() {
    }

    public  UserInterface getUserInterface(String userInterFaceType) {
        UserInterface userInterface;

        switch (userInterFaceType) {
            case "ConsoleInterface": {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                userInterface = new UserConsoleInterface(reader);
                break;
            }
            case "SwingInterface": {
                throw new IllegalArgumentException(REALIZATION_EXCEPTION_MESSAGE);
            }
            default:
                throw new IllegalArgumentException(ERROR_PARAMETER_MESSAGE);
        }
        return userInterface;
    }


}
