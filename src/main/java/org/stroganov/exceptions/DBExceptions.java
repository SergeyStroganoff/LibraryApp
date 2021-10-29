package org.stroganov.exceptions;

public class DBExceptions extends Exception {
    public DBExceptions(String message) {
        super(message);
    }

    public DBExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
