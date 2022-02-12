package org.stroganov.util;

import org.apache.log4j.Logger;
import org.stroganov.ConfigLoader;
import org.stroganov.exceptions.PropertiesException;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private static final Logger LOGGER = Logger.getLogger(PropertiesManager.class);
    private static Properties properties;
    private static final String ERROR_LOADING_CONFIGURATION_FILE_MESSAGE = "Error loading configuration file: ";


    private static void propertiesInitialization() {
        ConfigLoader configLoader = new ConfigLoader();
        try {
            properties = configLoader.getAppProp();
        } catch (IOException | PropertiesException e) {
            LOGGER.error(ERROR_LOADING_CONFIGURATION_FILE_MESSAGE + e.getMessage());
            System.exit(1);
        }
    }

    public static Properties getProperties() {
        if (properties == null) {
            propertiesInitialization();
        }
        return properties;
    }
}
