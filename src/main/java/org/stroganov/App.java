package org.stroganov;

import org.stroganov.exceptions.PropertiesException;
import org.stroganov.gui.UserInterface;
import org.stroganov.gui.UserInterfaceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Home library App
 * JSON data used as Data Base!
 *
 * @author Sergey Stroganov
 */
public class App {
    public static final String ERROR_LOADING_CONFIGURATION_FILE_MESSAGE = "Error loading configuration file:, program will be closed";
    public static  Properties properties;

    public static void main(String[] args) {

        UserInterface userInterface = UserInterfaceFactory.getUserInterface("ConsoleInterface");
        ConfigLoader configLoader = new ConfigLoader();
        try {
            properties = configLoader.getAppProp();
        } catch (IOException | PropertiesException e) {
            userInterface.showMessage(ERROR_LOADING_CONFIGURATION_FILE_MESSAGE);
        }
    }


}
