package org.stroganov.JsonDBAPI;

import org.apache.log4j.Logger;
import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UnrealizedFunctionalityException;
import org.stroganov.util.JSONUtil;

import java.io.IOException;
import java.util.List;

public class JsonDBLoader {

    Logger logger = org.apache.log4j.Logger.getLogger(JsonDBLoader.class);
    public static final String FILE_PATH_BOOKS = "src/main/resources/books.json";
    public static final String FILE_PATH_USERS = "src/main/resources/users.json";
    JSONUtil jsonUtil = new JSONUtil();
    JsonParser jsonParser = new JsonParser();


    public List<Book> loadBooks() throws UnrealizedFunctionalityException {


        String jsonBooksString = null;
        try {
            jsonBooksString = jsonUtil.readFileAsString(FILE_PATH_BOOKS);
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        return jsonParser.getListBooksFromDB(jsonBooksString);
    }

    public List<User> loadUsers() throws UnrealizedFunctionalityException {
        throw new UnrealizedFunctionalityException("Нет реализации loadUsers");
    }

}
