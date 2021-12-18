package org.stroganov.servlets.actions;

public enum CommandEnum {
    LOGIN("LOGIN"), LOGOUT("LOGOUT");

    CommandEnum(String name) {
        this.name = name;
    }

    final String name;

    public String getName() {
        return name;
    }
}


