package org.stroganov.JsonDBAPI;

import org.apache.log4j.Logger;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.FileUtil;

import java.io.IOException;
import java.util.List;

public interface JsonDBLoader {
    String DB_EXCEPTIONS = "DB files was not founded ore read :";
    Logger logger = org.apache.log4j.Logger.getLogger(JsonDBLoader.class);

    public static <T> List<T> loadEntities(String filePath, Class<T> tClass) throws DBExceptions {
        String jsonString;
        try {
            jsonString = FileUtil.readFileAsString(filePath);
        } catch (IOException e) {
            logger.error(e);
            throw new DBExceptions(DB_EXCEPTIONS + e.getMessage());
        }
        return JsonParser.getListEntitiesFromDB(jsonString, tClass);
    }
}
