package org.stroganov.history;

import com.google.gson.Gson;
import net.logstash.log4j.JSONEventLayoutV1;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.stroganov.App;
import org.stroganov.util.FileUtil;

import java.io.IOException;
import java.util.List;

/**
 * Save user action history in json format:
 * {
 * "timestamp":1352412458890,
 * "date":"Nov 8, 2012 10:07:38 PM",
 * "hostname":"michael1",
 * "username":"michael",
 * "level":"INFO",
 * "thread":"main",
 * "classname":"uk.me.mjt.log4jjson.SimpleJsonLayoutTest",
 * "filename":"SimpleJsonLayoutTest.java",
 * "linenumber":25,
 * "methodname":"testDemonstration",
 * "message":"Example of some logging"
 * }
 */

public class HistoryManager {

    private final Logger logger = Logger.getLogger(HistoryManager.class);
    String historyFileName = App.properties.getProperty("historyLogFileName");

    public HistoryManager() throws IOException {
        logger.addAppender(new FileAppender(new JSONEventLayoutV1(), historyFileName));
    }

    public void saveAction(String stringAction) {
        logger.info(stringAction);
    }

    public List<String> getHistoryEventsList() throws IOException {
        String jsonHistoryString = FileUtil.readFileAsString(historyFileName);
        Gson gson = new Gson();
        return gson.fromJson(jsonHistoryString, List.class);
    }
}
