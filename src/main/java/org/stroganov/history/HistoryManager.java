package org.stroganov.history;

import net.logstash.log4j.JSONEventLayoutV1;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.stroganov.App;

import java.io.IOException;

public class HistoryManager {

    private final Logger logger = Logger.getLogger(HistoryManager.class);

    public HistoryManager() throws IOException {
        String historyFileName = App.properties.getProperty("historyLogFileName");
        logger.addAppender(new FileAppender(new JSONEventLayoutV1(),historyFileName ));
    }

    public void saveAction(String stringAction) {
        logger.info(stringAction);
    }

}
