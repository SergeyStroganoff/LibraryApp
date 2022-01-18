package org.stroganov.util;

import org.stroganov.entities.User;

public class UserContainer {

    private static User user = null;

    private UserContainer() {
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserContainer.user = user;
    }
}
