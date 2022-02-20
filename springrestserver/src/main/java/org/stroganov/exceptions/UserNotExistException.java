package org.stroganov.exceptions;

public class UserNotExistException extends Exception {
    public UserNotExistException(String message) {
    }

    public UserNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
