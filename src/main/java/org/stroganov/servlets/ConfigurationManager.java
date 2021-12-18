package org.stroganov.servlets;

import org.stroganov.ConfigLoader;
import org.stroganov.exceptions.PropertiesException;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static Properties properties;

    private ConfigurationManager() {
    }

    public static void init() {
        ConfigLoader configLoader = new ConfigLoader();
        try {
            properties = configLoader.getAppProp();
        } catch (IOException | PropertiesException e) {
            System.exit(1);
        }
    }

    public static String getProperties(String key) {
        return properties != null ? properties.getProperty(key) : null;
    }
}
