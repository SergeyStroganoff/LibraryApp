package org.stroganov.JsonDBAPI;

import org.apache.log4j.Logger;
import org.stroganov.App;
import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.JSONUtil;

import java.io.IOException;
import java.util.List;

public class JsonDBLoader {
    Logger logger = org.apache.log4j.Logger.getLogger(JsonDBLoader.class);
    JsonParser jsonParser = new JsonParser();

    public List<Book> loadBooks() throws DBExceptions {
        String jsonBooksString = null;
        try {
            jsonBooksString = JSONUtil.readFileAsString(App.properties.getProperty("booksJsonFile"));
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions("DB files was not founded ore read:" + e.getMessage());
        }
        return jsonParser.getListBooksFromDB(jsonBooksString);
    }

    public List<User> loadUsers() {
        return null;
    }

}
