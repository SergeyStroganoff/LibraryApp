package org.stroganov.JsonDBAPI;

import org.apache.log4j.Logger;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.FileUtil;

import java.io.IOException;
import java.util.List;

public class JsonDBLoader {
    public static final String DB_EXCEPTIONS = "DB files was not founded ore read :";
    private final Logger logger = org.apache.log4j.Logger.getLogger(JsonDBLoader.class);
    private final JsonParser jsonParser = new JsonParser();


    public List<Book> loadBooks(String filePath) throws DBExceptions {
        String jsonString;
        try {
            jsonString = FileUtil.readFileAsString(filePath);
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions(DB_EXCEPTIONS + e.getMessage());
        }
        return jsonParser.getListBooksFromDB(jsonString);
    }

    public List<User> loadUsers(String filePath) throws DBExceptions {
        String jsonString;
        try {
            jsonString = FileUtil.readFileAsString(filePath);
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions(DB_EXCEPTIONS + e.getMessage());
        }
        return jsonParser.getListUsersFromDB(jsonString);
    }

    public List<BookMark> loadBookMarks(String filePath) throws DBExceptions {
        String jsonString;
        try {
            jsonString = FileUtil.readFileAsString(filePath);
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions(DB_EXCEPTIONS + e.getMessage());
        }
        return jsonParser.getListBookMarkFromDB(jsonString);
    }

    public List<Author> loadAuthors(String filePath) throws DBExceptions {
        String jsonString;
        try {
            jsonString = FileUtil.readFileAsString(filePath);
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions(DB_EXCEPTIONS + e.getMessage());
        }
        return jsonParser.getListAuthorsFromDB(jsonString);
    }
}
