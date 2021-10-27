package org.stroganov.JsonDBAPI;

import org.stroganov.entities.Author;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.util.FileUtil;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class JsonDBSaver {
    private Properties properties;

    public JsonDBSaver(Properties properties) {
        this.properties = properties;
    }

    public void saveEntityListToJsonFormatFile(List<?> list) throws IOException {
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
