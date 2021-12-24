package org.stroganov;

import org.apache.log4j.Logger;
import org.stroganov.dialogue.InitialDialogue;
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
    private static final Logger LOGGER = Logger.getLogger(App.class);
    private static final String ERROR_LOADING_CONFIGURATION_FILE_MESSAGE = "Error loading configuration file: ";
    private static final String PROGRAM_WILL_BE_CLOSED = "Program will be closed";
    public static Properties properties;
    public static final String APP_PROPERTIES = "/app.properties";

    public static void main(String[] args) {

        UserInterface userInterface = UserInterfaceFactory.getUserInterface("ConsoleInterface");
        ConfigLoader configLoader = new ConfigLoader();
        try {
            properties = configLoader.getAppProp(APP_PROPERTIES);
        } catch (PropertiesException | IOException e) {
            LOGGER.error(ERROR_LOADING_CONFIGURATION_FILE_MESSAGE + e.getMessage());
            userInterface.showMessage(ERROR_LOADING_CONFIGURATION_FILE_MESSAGE + e.getMessage());
            userInterface.showMessage(PROGRAM_WILL_BE_CLOSED);
            System.exit(1);
        }

        InitialDialogue initialDialogue = new InitialDialogue(userInterface);
        initialDialogue.runDialogue();
    }
}
