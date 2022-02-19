package org.stroganov.exceptions;

public class AuthorSavingException extends Throwable {
    public AuthorSavingException(String message) {
    }

    public AuthorSavingException(String message, Throwable cause) {
        super(message, cause);
    }
}
