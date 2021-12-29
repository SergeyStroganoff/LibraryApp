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

    private static final Logger HISTORY_LOGGER = Logger.getLogger(WEBHistoryManager.class);

    static {
        try {
            HISTORY_LOGGER.addAppender(new FileAppender(new JSONEventLayoutV1(), ConfigurationManager.getProperties("historyLogFileName")));
        } catch (IOException e) {
            HISTORY_LOGGER.error("Error when change logger appender " + e.getMessage());
        }
    }

    private WEBHistoryManager() {
    }

    public static void saveAction(String userLogin, String stringAction) {
        HISTORY_LOGGER.info("UserLogin: " + userLogin + ". Action: " + stringAction);
    }

    public static List<HistoryEvent> getHistoryEventsList() throws IOException {
        Gson gson = new Gson();
        List<HistoryEvent> historyEvents = new ArrayList<>();
        List<String> stringList = FileUtil.getListOfStringsFile(ConfigurationManager.getProperties("historyLogFileName"));
        stringList.forEach(o -> historyEvents.add(gson.fromJson(o, HistoryEvent.class)));
        return historyEvents;
    }


}
