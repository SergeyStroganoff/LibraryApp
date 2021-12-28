package org.stroganov.history;

import com.google.gson.Gson;
import net.logstash.log4j.JSONEventLayoutV1;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.stroganov.entities.HistoryEvent;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WEBHistoryManager {

    private static final Logger logger = Logger.getLogger(WEBHistoryManager.class);
    private static final String HISTORY_FILE_NAME = ConfigurationManager.getProperties("historyLogFileName");

    static {
        try {
            logger.addAppender(new FileAppender(new JSONEventLayoutV1(), HISTORY_FILE_NAME));
        } catch (IOException e) {
            logger.error("Error when change logger appender " + e.getMessage());
        }
    }

    public static void saveAction(String userLogin, String stringAction) {
        logger.info("UserLogin: " + userLogin + ". Action: " + stringAction);
    }

    public static List<HistoryEvent> getHistoryEventsList() throws IOException {
        Gson gson = new Gson();
        List<HistoryEvent> historyEvents = new ArrayList<>();
        List<String> stringList = FileUtil.getListOfStringsFile(HISTORY_FILE_NAME);
        stringList.forEach(o -> historyEvents.add(gson.fromJson(o, HistoryEvent.class)));
        return historyEvents;
    }


}
