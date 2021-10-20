package org.stroganov.JsonDBAPI;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UnrealizedFunctionalityException;

import java.util.List;

public class JsonParser {

    public List<Book> getListBooksFromDB(String jsonString) {
        return null;
    }

    public List<User> getListUsersFromDB(String jsonString) {

        return null;
    }

    public Book getBookFromJsonElement(JsonElement jsonElement) throws UnrealizedFunctionalityException {
        JsonObject currentJsonObject = jsonElement.getAsJsonObject();
        BookBuilder bookBuilder = new BookBuilder(currentJsonObject);
        return bookBuilder.buildBook();
    }

    public User getUserFromJsonElement(JsonElement jsonElement) throws UnrealizedFunctionalityException {
        JsonObject currentJsonObject = jsonElement.getAsJsonObject();
        UserBuilder userBuilder = new UserBuilder(currentJsonObject);
        return userBuilder.buildUser();
    }

}
