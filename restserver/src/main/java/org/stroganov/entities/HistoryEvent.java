package org.stroganov.entities;

import com.google.gson.annotations.SerializedName;

public class HistoryEvent {
    // {"@timestamp":"2021-10-27T10:53:44.096Z",
    // "source_host":"ServerPC",
    // "file":"HistoryManager.java",
    // "method":"saveAction",
    // "level":"INFO",
    // "line_number":"42",
    // "thread_name":"main",
    // "@version":1,
    // "logger_name":"org.stroganov.history.HistoryManager",
    // "message":"User 111sergey90 entered in system",
    // "class":"org.stroganov.history.HistoryManager",
    // "mdc":{}}
    @SerializedName("@timestamp")
    private final String timestamp;
    @SerializedName("source_host")
    private final String sourceHost;
    private final String file;
    private final String method;
    private final String level;
    @SerializedName("line_number")
    private final String lineNumber;
    @SerializedName("thread_name")
    private final String threadName;
    @SerializedName("@version")
    private final String version;
    @SerializedName("logger_name")
    private final String loggerName;
    private final String message;
    @SerializedName("class")
    private final String sourceClass;
    private final Object mdc;


    public HistoryEvent(String timestamp, String sourceHost, String file, String method,
                        String level, String lineNumber, String threadName, String version,
                        String loggerName, String message, String sourceClass, Object mdc) {
        this.timestamp = timestamp;
        this.sourceHost = sourceHost;
        this.file = file;
        this.method = method;
        this.level = level;
        this.lineNumber = lineNumber;
        this.threadName = threadName;
        this.version = version;
        this.loggerName = loggerName;
        this.message = message;
        this.sourceClass = sourceClass;
        this.mdc = mdc;
    }

    @Override
    public String toString() {
        return "Data and Time: " + timestamp + ", message= " + message;

    }
}
