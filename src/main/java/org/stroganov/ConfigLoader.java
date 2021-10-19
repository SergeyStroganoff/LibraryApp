package org.stroganov;

import org.stroganov.exceptions.PropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static final String FILE_HAS_INCORRECT_FORMAT = "configuration file has incorrect format";
    public static final String FILE_WASN_T_FOUND = "configuration file wasn't found";
    public static final String APP_PROPERTIES = "/app.properties";


    public Properties getAppProp() throws IOException, PropertiesException {
        Properties properties = new Properties();
        InputStream inputStream = App.class.getResourceAsStream(APP_PROPERTIES);
        if (inputStream == null) {
            throw new PropertiesException(FILE_WASN_T_FOUND);
        }
        properties.load(inputStream);
        if (!propertyVerify(properties)) {
            throw new PropertiesException(FILE_HAS_INCORRECT_FORMAT);
        }
        return properties;
    }

    public boolean propertyVerify(Properties properties) {
        String historyLogFileName = properties.getProperty("historyLogFileName");
        if (!historyLogFileName.matches("^\\d+")) {
            return false;
        }
        return true;
    }
}
