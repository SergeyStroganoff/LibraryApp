package org.stroganov.JsonDBAPI;

import org.apache.log4j.Logger;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.FileUtil;

import java.io.IOException;
import java.util.List;

public class JsonDBLoader {
    public static final String DB_EXCEPTIONS = "DB files was not founded ore read :";
    private final Logger logger = org.apache.log4j.Logger.getLogger(JsonDBLoader.class);
    private final JsonParser jsonParser = new JsonParser();

    public <T> List<T> loadEntities(String filePath, Class<T> tClass) throws DBExceptions {
        String jsonString;
        try {
            jsonString = FileUtil.readFileAsString(filePath);
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions(DB_EXCEPTIONS + e.getMessage());
        }
        return jsonParser.getListEntitiesFromDB(jsonString, tClass);
    }
}
