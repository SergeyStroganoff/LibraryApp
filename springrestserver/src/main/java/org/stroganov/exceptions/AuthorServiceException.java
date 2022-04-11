package org.stroganov.exceptions;

public class AuthorServiceException extends Throwable {
    public AuthorServiceException(String message) {
    }

    public AuthorServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
