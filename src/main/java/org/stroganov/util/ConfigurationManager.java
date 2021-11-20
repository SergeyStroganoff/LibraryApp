package org.stroganov.util;

import org.stroganov.ConfigLoader;
import org.stroganov.exceptions.PropertiesException;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static Properties properties;

    static {
        try {
            init();
        } catch (PropertiesException | IOException e) {
            e.printStackTrace();
        }
    }

    private ConfigurationManager() {
    }

    public static void init() throws PropertiesException, IOException {
        ConfigLoader configLoader = new ConfigLoader();
        properties = configLoader.getAppProp();

    }

    public static String getProperties(String key) {
        return properties != null ? properties.getProperty(key) : null;
    }
}