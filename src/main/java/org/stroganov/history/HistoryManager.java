package org.stroganov.history;

import net.logstash.log4j.JSONEventLayoutV1;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class HistoryManager {

    private Logger logger = Logger.getLogger(HistoryManager.class);

    public HistoryManager() {
        logger.addAppender(new FileAppender(new JSONEventLayoutV1(), logFileName));
    }

    public void saveAction(String stringAction){
        logger.info(stringAction);
    }

}
