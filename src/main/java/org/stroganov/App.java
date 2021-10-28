package org.stroganov;

import org.apache.log4j.Logger;
import org.stroganov.dialogue.InitialDialogue;
import org.stroganov.exceptions.PropertiesException;
import org.stroganov.exceptions.UnrealizedFunctionalityException;
import org.stroganov.gui.UserInterface;
import org.stroganov.gui.UserInterfaceFactory;
import org.stroganov.history.HistoryManager;

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

    public static void main(String[] args) throws UnrealizedFunctionalityException {

        UserInterface userInterface = UserInterfaceFactory.getUserInterface("ConsoleInterface");
        ConfigLoader configLoader = new ConfigLoader();
        try {
            properties = configLoader.getAppProp();
        } catch (IOException | PropertiesException e) {
            LOGGER.error(ERROR_LOADING_CONFIGURATION_FILE_MESSAGE + e.getMessage());
            userInterface.showMessage(ERROR_LOADING_CONFIGURATION_FILE_MESSAGE + e.getMessage());
            userInterface.showMessage(PROGRAM_WILL_BE_CLOSED);
            System.exit(1);
        }

        InitialDialogue initialDialogue = new InitialDialogue(userInterface);
        initialDialogue.runDialogue();

    }
}
