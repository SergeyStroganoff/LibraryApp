package org.stroganov.history;

import com.google.gson.Gson;
import net.logstash.log4j.JSONEventLayoutV1;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.stroganov.App;
import org.stroganov.entities.HistoryEvent;
import org.stroganov.util.FileUtil;
import org.stroganov.util.PropertiesManager;

import java.io.IOException;
import java.util.ArrayList;
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
    final String historyFileName = PropertiesManager.getProperties().getProperty("historyLogFileName");

    public HistoryManager() throws IOException {
        logger.addAppender(new FileAppender(new JSONEventLayoutV1(), historyFileName));
    }

    public void saveAction(String stringAction) {
        logger.info(stringAction);
    }

    public List<HistoryEvent> getHistoryEventsList() throws IOException {
        Gson gson = new Gson();
        List<HistoryEvent> historyEvents = new ArrayList<>();
        List<String> stringList = FileUtil.getListOfStringsFile(historyFileName);
        stringList.forEach(o -> historyEvents.add(gson.fromJson(o, HistoryEvent.class)));
        return historyEvents;
    }
}
