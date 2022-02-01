package org.stroganov.restservice;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import org.stroganov.JsonDBAPI.JsonParser;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.*;
import org.stroganov.util.PropertiesManager;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LibraryRestServiceClient implements LibraryDAO {
    public static final String FAILED_HTTP_ERROR_CODE = "Failed : HTTP error code : ";
    private static volatile LibraryRestServiceClient instance;
    private final Logger logger = Logger.getLogger(LibraryRestServiceClient.class);
    private final Client client = Client.create();
    private final Gson gson = new Gson();
    private final String restServiceURL = PropertiesManager.getProperties().getProperty("restServiceURL");

    public static synchronized LibraryRestServiceClient getInstance() {
        if (instance == null) {
            synchronized (LibraryRestServiceClient.class) {
                if (instance == null) {
                    instance = new LibraryRestServiceClient();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean addBook(Book book) throws IOException {
        return false;
    }

    @Override
    public boolean addBook(List<Book> bookList) throws IOException {
        return false;
    }

    @Override
    public boolean deleteBook(Book book) throws IOException {
        return false;
    }

    @Override
    public Book findBook(String numberISBN) {
        try {
            String responseJSON = getJSONStringFromServer("/search/" + numberISBN);
            return gson.fromJson(responseJSON, Book.class);
        } catch (Exception e) {
            logger.error("Error in findBook method", e);
            return null;
        }
    }

    @Override
    public List<Book> findBooksByPartName(String partOfName) {
        try {
            String responseJSON = getJSONStringFromServer("/search/partOfName/" + partOfName);
            return JsonParser.getListEntitiesFromDB(responseJSON, Book.class);
        } catch (Exception e) {
            logger.error("Error in findBooksByPartName method", e);
            return Collections.emptyList();
        }
    }


    @Override
    public boolean addUser(User user) throws IOException {
        return false;
    }

    @Override
    public User findUser(String userLogin) {
        try {
            String responseJSON = getJSONStringFromServer("/user/" + userLogin);
            System.out.println(responseJSON);
            return gson.fromJson(responseJSON, User.class);
        } catch (Exception e) {
            logger.error("Error in client method", e);
            return null;
        }
    }

    @Override
    public boolean deleteUser(User user) throws IOException {
        return false;
    }

    @Override
    public boolean blockUser(User user) throws IOException {
        return false;
    }

    @Override
    public boolean unblockUser(User user) throws IOException {
        return false;
    }

    @Override
    public boolean addBookMark(BookMark bookMark) throws IOException {
        return false;
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) throws IOException {
        return false;
    }

    @Override
    public boolean addAuthor(Author author) {
        String authorJSONFormat = gson.toJson(author, Author.class);
        String result = postJSONStringToServer("/author", authorJSONFormat);
        return gson.fromJson(result, Boolean.class);
        // return "true".equals(result);
    }

    @Override
    public Author findAuthor(String authorName) {
        try {
            String responseJSON = getJSONStringFromServer("/author/" + authorName);
            return gson.fromJson(responseJSON, Author.class);
        } catch (Exception e) {
            logger.error("Error in findAuthor method", e);
            return null;
        }
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) throws IOException {
        String authorJSONFormat = gson.toJson(author, Author.class);
        String result = deleteJSONStringToServer("/author", authorJSONFormat);
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public List<Book> findBooksByPartAuthorName(String partAuthorName) {
        try {
            String responseJSON = getJSONStringFromServer("/search/partAuthorName/" + partAuthorName);
            return JsonParser.getListEntitiesFromDB(responseJSON, Book.class);
        } catch (Exception e) {
            logger.error("Error in findBooksByPartName method", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> findBooksByYearsRange(int firstYear, int secondYear) {
        return null;
    }

    @Override
    public List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        return null;
    }

    @Override
    public List<Book> findBooksWithUserBookMarks(User user) {
        return null;
    }

    @Override
    public List<BookMark> findUserBookMarks(User user) {
        return null;
    }

    @Override
    public boolean addHistoryEvent(History history) {
        return false;
    }

    @Override
    public List<History> getAllHistory() {
        return null;
    }

    private String getJSONStringFromServer(String pathParam) {
        WebResource webResource = client.resource(restServiceURL + pathParam);
        // webResource.header(HttpHeaders.AUTHORIZATION,"Bearer " + AuthenticationServiceClient.getJWTToken());
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException(FAILED_HTTP_ERROR_CODE + response.getStatus());
        }
        return response.getEntity(String.class);
    }

    private String postJSONStringToServer(String pathParam, String jsonString) {
        WebResource webResource = client.resource(restServiceURL + pathParam);
        ClientResponse response = webResource.accept("application/json")
                .post(ClientResponse.class, jsonString);
        if (response.getStatus() != 200) {
            throw new RuntimeException(FAILED_HTTP_ERROR_CODE + response.getStatus());
        }
        return response.getEntity(String.class);
    }

    private String deleteJSONStringToServer(String pathParam, String jsonString) {
        WebResource webResource = client.resource(restServiceURL + pathParam);
        ClientResponse response = webResource.accept("application/json")
                .delete(ClientResponse.class, jsonString);
        if (response.getStatus() != 200) {
            throw new RuntimeException(FAILED_HTTP_ERROR_CODE + response.getStatus());
        }
        return response.getEntity(String.class);
    }

}