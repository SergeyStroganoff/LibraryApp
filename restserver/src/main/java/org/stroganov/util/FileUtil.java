package org.stroganov.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    private FileUtil() {
    }

    private static final Logger logger = Logger.getLogger(FileUtil.class);

    public static void saveStringToFile(String filePath, String jsonString) throws IOException {
        try {
            Files.write(Paths.get(filePath), jsonString.getBytes());
        } catch (IOException e) {
            logger.error(e.getMessage() + filePath);
            throw e;
        }
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
