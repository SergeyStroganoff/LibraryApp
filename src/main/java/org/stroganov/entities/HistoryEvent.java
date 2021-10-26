package org.stroganov.entities;

public class HistoryEvent {
    String timestamp;
    String date;
    String hostname;
    String username;
    String level;
    String thread;
    String classname;
    String filename;
    String linenumber;
    String methodName;
    String message;

    public HistoryEvent(String timestamp, String date, String hostname, String username, String level,
                        String thread, String classname, String filename, String linenumber, String methodName,
                        String message) {
        this.timestamp = timestamp;
        this.date = date;
        this.hostname = hostname;
        this.username = username;
        this.level = level;
        this.thread = thread;
        this.classname = classname;
        this.filename = filename;
        this.linenumber = linenumber;
        this.methodName = methodName;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Data: " + date + " Message: " + message;
    }
}
