package org.stroganov.exceptions;

public class ConsoleInterfaceError extends Throwable {

    public ConsoleInterfaceError(String message) {
        super(message);
    }

    public ConsoleInterfaceError(String message, Throwable cause) {
        super(message, cause);
    }
}
