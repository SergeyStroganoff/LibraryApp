package org.stroganov.util;

import org.apache.log4j.Logger;
import org.stroganov.entities.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private FileUtil() {
    }

    private static final Logger logger = Logger.getLogger(FileUtil.class);

    public static boolean saveStringToFile(String filePath, String jsonString) {
        try {
            Files.write(Paths.get(filePath), jsonString.getBytes());
        } catch (IOException e) {
            logger.error(e.getMessage() + filePath);
            return false;
        }
        return true;
    }

    public static String readFileAsString(String filePath) throws IOException {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            logger.error(e.getMessage() + filePath);
            throw e;
        }
    }

    public static List<String> getListOfStringsFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.readAllLines(path);
    }


}
