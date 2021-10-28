package org.stroganov.JsonDBAPI;

import org.apache.log4j.Logger;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.util.FileUtil;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class JsonDBSaver {
    private final Properties properties;
    Logger logger = Logger.getLogger(JsonDBSaver.class);

    public JsonDBSaver(Properties properties) {
        this.properties = properties;
    }

    public void saveEntityListToJsonFormatFile(List<?> list) throws IOException {
        if (list == null || list.isEmpty()) {
            logger.error("Input in 'saveEntityListToJsonFormatFile method'  List of saved entities mustn't be null ore empty");
            throw new IllegalArgumentException("List of saved entities mustn't be null ore empty");
        }
        String path = null;
        Object o = list.get(0);
        if (o instanceof Book) {
            path = properties.getProperty("booksJsonFile");
        }
        if (o instanceof User) {
            path = properties.getProperty("usersJsonFile");
        }
        if (o instanceof BookMark) {
            path = properties.getProperty("bookMarksJsonFile");
        }
        if (o instanceof Author) {
            path = properties.getProperty("authorsJsonFile");
        }
        FileUtil.saveStringToFile(path, JsonSerializer.entitySerializer(list));
    }
}
