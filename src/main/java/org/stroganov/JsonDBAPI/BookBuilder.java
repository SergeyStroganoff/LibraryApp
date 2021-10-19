package org.stroganov.JsonDBAPI;

import com.google.gson.JsonObject;
import org.stroganov.entities.Book;
import org.stroganov.exceptions.UnrealizedFunctionalityException;



public class BookBuilder {

    private JsonObject currentJsonObject;

    public BookBuilder(JsonObject currentJsonObject) {
        this.currentJsonObject = currentJsonObject;
    }

    public Book buildBook() throws UnrealizedFunctionalityException {
        throw new UnrealizedFunctionalityException("buildBook not realized");
    }
}
