package org.stroganov.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
    // класс извлекает информацию из файла messages.properties
    private MessageManager() {
    }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}


