package org.stroganov;

import org.stroganov.exceptions.PropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static final String FILE_WAS_NOT_FOUND = "configuration file wasn't found";

    public Properties getAppProp(String propertyPath) throws PropertiesException, IOException {
        Properties properties = new Properties();
        InputStream inputStream = ConfigLoader.class.getResourceAsStream(propertyPath);
        if (inputStream == null) {
            throw new PropertiesException(FILE_WAS_NOT_FOUND);
        }
        properties.load(inputStream);
        return properties;
    }
}
