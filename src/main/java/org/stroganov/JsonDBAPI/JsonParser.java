package org.stroganov.JsonDBAPI;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UnrealizedFunctionalityException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    Gson gson = new Gson();

    public List<Book> getListBooksFromDB(String jsonString) {
        Type itemsListType = new TypeToken<List<Book>>() {
        }.getType();
        List<Book> bookList = gson.fromJson(jsonString, itemsListType);
        if (bookList == null) {
            bookList = new ArrayList<>();
        }
        return bookList;
    }

    public List<User> getListUsersFromDB(String jsonString) {
        Type itemsListType = new TypeToken<List<User>>() {
        }.getType();
        List<User> userList = gson.fromJson(jsonString, itemsListType);
        if (userList == null) {
            userList = new ArrayList<>();
        }
        return userList;
    }

    public List<BookMark> getListBookMarkFromDB(String jsonString) {
        Type itemsListType = new TypeToken<List<BookMark>>() {
        }.getType();
        List<BookMark> bookMarkList = gson.fromJson(jsonString, itemsListType);
        if (bookMarkList == null) {
            bookMarkList = new ArrayList<>();
        }
        return bookMarkList;
    }

    public List<Author> getListAuthorsFromDB(String jsonString) {
        Type itemsListType = new TypeToken<List<Author>>() {
        }.getType();
        List<Author> authorList = gson.fromJson(jsonString, itemsListType);
        if (authorList == null) {
            authorList = new ArrayList<>();
        }
        return authorList;
    }
}
