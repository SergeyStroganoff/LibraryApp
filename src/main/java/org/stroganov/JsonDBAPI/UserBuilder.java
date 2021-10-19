package org.stroganov.JsonDBAPI;

import com.google.gson.JsonObject;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UnrealizedFunctionalityException;

public class UserBuilder {

    private JsonObject currentJsonObject;

    public UserBuilder(JsonObject currentJsonObject) {
        this.currentJsonObject = currentJsonObject;
    }

    public User buildUser() throws UnrealizedFunctionalityException {
        throw new UnrealizedFunctionalityException("buildUser not realized");
    }

}
