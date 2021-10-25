package org.stroganov.JsonDBAPI;

import org.apache.log4j.Logger;
import org.stroganov.App;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.FileUtil;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class JsonDBLoader {
    public static final String DB_EXCEPTIONS = "DB files was not founded ore read :";
    private Logger logger = org.apache.log4j.Logger.getLogger(JsonDBLoader.class);
    private JsonParser jsonParser = new JsonParser();
    private Properties properties;

    public JsonDBLoader(Properties properties) {
        this.properties = properties;
    }

    public List<Book> loadBooks() throws DBExceptions {
        String jsonString;
        try {
            jsonString = FileUtil.readFileAsString(properties.getProperty("booksJsonFile"));
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions(DB_EXCEPTIONS + e.getMessage());
        }
        return jsonParser.getListBooksFromDB(jsonString);
    }

    public List<User> loadUsers() throws DBExceptions {
        String jsonString;
        try {
            jsonString = FileUtil.readFileAsString(properties.getProperty("usersJsonFile"));
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions(DB_EXCEPTIONS + e.getMessage());
        }
        return jsonParser.getListUsersFromDB(jsonString);
    }

    public List<BookMark> loadBookMarks() throws DBExceptions {
        String jsonString;
        try {
            jsonString = FileUtil.readFileAsString(properties.getProperty("bookMarksJsonFile"));
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions(DB_EXCEPTIONS + e.getMessage());
        }
        return jsonParser.getListBookMarkFromDB(jsonString); // TODO: 20.10.2021  
    }

    public List<Author> loadAuthors() throws DBExceptions {
        String jsonString;
        try {
            jsonString = FileUtil.readFileAsString(properties.getProperty("authorsJsonFile"));
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions(DB_EXCEPTIONS + e.getMessage());
        }
        return jsonParser.getListAuthorsFromDB(jsonString); // TODO: 20.10.2021  
    }
}
