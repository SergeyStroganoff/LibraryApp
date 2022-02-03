package org.stroganov.restservice;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import org.stroganov.JsonDBAPI.JsonParser;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.*;
import org.stroganov.exceptions.ClientServiceException;
import org.stroganov.util.PropertiesManager;

import javax.ws.rs.core.HttpHeaders;
import java.util.Collections;
import java.util.List;

public class LibraryRestServiceClient implements LibraryDAO {
    public static final String FAILED_HTTP_ERROR_CODE = "Failed : HTTP error code : ";
    public static final String USER_PATH = "/user/";
    private static volatile LibraryRestServiceClient instance;
    private final Logger logger = Logger.getLogger(LibraryRestServiceClient.class);
    private final Client client = Client.create();
    private final Gson gson = new Gson();
    private final String restServiceURL = PropertiesManager.getProperties().getProperty("restServiceURL");

    public static LibraryRestServiceClient getInstance() {
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
    public boolean addBook(Book book) {
        return false;
    }

    @Override
    public boolean addBook(List<Book> bookList) {
        return false;
    }

    @Override
    public boolean deleteBook(Book book) {
        String bookISBNJSONFormat = gson.toJson(book.getNumberISBN(), String.class);
        String result;
        try {
            result = deleteJSONStringToServer("/book", bookISBNJSONFormat);
        } catch (ClientServiceException e) {
            logger.error("Error deleteBook", e);
            return false;
        }
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public Book findBook(String numberISBN) {
        try {
            String responseJSON = getJSONStringFromServer("/search/" + numberISBN, true);
            return gson.fromJson(responseJSON, Book.class);
        } catch (Exception e) {
            logger.error("Error in findBook method", e);
            return null;
        }
    }

    @Override
    public List<Book> findBooksByPartName(String partOfName) {
        try {
            String responseJSON = getJSONStringFromServer("/search/partOfName/" + partOfName, true);
            return JsonParser.getListEntitiesFromJsonString(responseJSON, Book.class);
        } catch (Exception e) {
            logger.error("Error in findBooksByPartName method", e);
            return Collections.emptyList();
        }
    }


    @Override
    public boolean addUser(User user) {
        String userJSONFormat = gson.toJson(user, BookMark.class);
        String result;
        try {
            result = postJSONStringToServer(USER_PATH, userJSONFormat);
        } catch (Exception e) {
            logger.error("Error addUser " + user.getLogin(), e);
            return false;
        }
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public User findUser(String userLogin) {
        try {
            String responseJSON = getJSONStringFromServer(USER_PATH + userLogin, false);
            return gson.fromJson(responseJSON, User.class);
        } catch (Exception e) {
            logger.error("Error in client method", e);
            return null;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        String userLoginJSONFormat = gson.toJson(user.getLogin(), String.class);
        String result;
        try {
            result = deleteJSONStringToServer(USER_PATH, userLoginJSONFormat);
        } catch (ClientServiceException e) {
            logger.error("Error deleteUser " + user.getLogin(), e);
            return false;
        }
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public boolean blockUser(User user) {
        String userLoginJSONFormat = gson.toJson(user.getLogin(), String.class);
        String result;
        try {
            result = postJSONStringToServer("/user/block/", userLoginJSONFormat);
        } catch (Exception e) {
            logger.error("Error blockUser " + user.getLogin(), e);
            return false;
        }
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public boolean unblockUser(User user) {
        String userLoginJSONFormat = gson.toJson(user.getLogin(), String.class);
        String result;
        try {
            result = postJSONStringToServer("/user/unblock/", userLoginJSONFormat);
        } catch (Exception e) {
            logger.error("Error unBlockUser " + user.getLogin(), e);
            return false;
        }
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public boolean addBookMark(BookMark bookMark) {
        String bookMArkJSONFormat = gson.toJson(bookMark, BookMark.class);
        String result;
        try {
            result = postJSONStringToServer("/bookMark/", bookMArkJSONFormat);
        } catch (Exception e) {
            logger.error("Error addBookMark " + bookMark, e);
            return false;
        }
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) {
        String userLoginJSONFormat = gson.toJson(bookMark.getId(), Integer.class);
        String result;
        try {
            result = deleteJSONStringToServer("/user", userLoginJSONFormat);
        } catch (ClientServiceException e) {
            logger.error("Error deleteBookMark " + bookMark.getId(), e);
            return false;
        }
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public boolean addAuthor(Author author) {
        String authorJSONFormat = gson.toJson(author, Author.class);
        String result = postJSONStringToServer("/author", authorJSONFormat);
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public Author findAuthor(String authorName) {
        try {
            String responseJSON = getJSONStringFromServer("/author/" + authorName, true);
            return gson.fromJson(responseJSON, Author.class);
        } catch (Exception e) {
            logger.error("Error in findAuthor method", e);
            return null;
        }
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) {
        String authorJSONFormat = gson.toJson(author.getAuthorName(), String.class);
        String result;
        try {
            result = deleteJSONStringToServer("/author", authorJSONFormat);
        } catch (ClientServiceException e) {
            logger.error("Error in deleteAuthorWithAllHisBooks", e);
            return false;
        }
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public List<Book> findBooksByPartAuthorName(String partAuthorName) {
        try {
            String responseJSON = getJSONStringFromServer("/search/partAuthorName/" + partAuthorName, true);
            return JsonParser.getListEntitiesFromJsonString(responseJSON, Book.class);
        } catch (Exception e) {
            logger.error("Error in findBooksByPartName method", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> findBooksByYearsRange(int firstYear, int secondYear) {
        try {
            WebResource webResource = client.resource(restServiceURL + "/search/findBooksByYearsRange/");
            webResource.header(HttpHeaders.AUTHORIZATION, "Bearer " + AuthenticationServiceClient.getJwtToken());
            ClientResponse response = webResource.
                    queryParam("firstYear", String.valueOf(firstYear)).
                    queryParam("secondYear", String.valueOf(secondYear)).
                    accept("application/json")
                    .get(ClientResponse.class);
            if (response.getStatus() != 200) {
                logger.error(FAILED_HTTP_ERROR_CODE + response.getStatus());
                throw new RuntimeException(FAILED_HTTP_ERROR_CODE + response.getStatus());
            }
            String responseJSON = response.getEntity(String.class);
            return JsonParser.getListEntitiesFromJsonString(responseJSON, Book.class);
        } catch (Exception e) {
            logger.error("Error in findBooksByPartName method", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        try {
            WebResource webResource = client.resource(restServiceURL + "/search/findBooksByParameters/");
            webResource.header(HttpHeaders.AUTHORIZATION, "Bearer " + AuthenticationServiceClient.getJwtToken());
            ClientResponse response = webResource.
                    queryParam("bookYear", String.valueOf(bookYear)).
                    queryParam("bookPages", String.valueOf(bookPages)).
                    queryParam("partBookName", partBookName).
                    accept("application/json")
                    .get(ClientResponse.class);
            if (response.getStatus() != 200) {
                logger.error(FAILED_HTTP_ERROR_CODE + response.getStatus());
                throw new RuntimeException(FAILED_HTTP_ERROR_CODE + response.getStatus());
            }
            String responseJSON = response.getEntity(String.class);
            return JsonParser.getListEntitiesFromJsonString(responseJSON, Book.class);
        } catch (Exception e) {
            logger.error("Error in findBooksByPartName method", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> findBooksWithUserBookMarks(User user) {
        try {
            String responseJSON = getJSONStringFromServer("/search/findBooksWithUserBookMarks/" + user.getLogin(), true);
            return JsonParser.getListEntitiesFromJsonString(responseJSON, Book.class);
        } catch (Exception e) {
            logger.error("Error in findBooksWithUserBookMarks method", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<BookMark> findUserBookMarks(User user) {
        try {
            String responseJSON = getJSONStringFromServer("/bookMark/" + user.getLogin(), true);
            return JsonParser.getListEntitiesFromJsonString(responseJSON, BookMark.class);
        } catch (Exception e) {
            logger.error("Error in findBooksWithUserBookMarks method", e);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean addHistoryEvent(History history) {
        String historyEventJSONFormat = gson.toJson(history, History.class);
        String result;
        try {
            result = postJSONStringToServer("/bookMark/", historyEventJSONFormat);
        } catch (Exception e) {
            logger.error("Error addHistoryEvent " + history, e);
            return false;
        }
        return gson.fromJson(result, Boolean.class);
    }

    @Override
    public List<History> getAllHistory() {
        return null;
    }

    private String getJSONStringFromServer(String pathParam, boolean isJWSTokenNeeded) {
        WebResource webResource = client.resource(restServiceURL + pathParam);
        if (isJWSTokenNeeded) {
            webResource.header(HttpHeaders.AUTHORIZATION, "Bearer " + AuthenticationServiceClient.getJwtToken());
        }
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException(FAILED_HTTP_ERROR_CODE + response.getStatus());
        }
        return response.getEntity(String.class);
    }

    private String postJSONStringToServer(String pathParam, String jsonString) {
        WebResource webResource = client.resource(restServiceURL + pathParam);
        webResource.header(HttpHeaders.AUTHORIZATION, "Bearer " + AuthenticationServiceClient.getJwtToken());
        ClientResponse response = webResource.accept("application/json")
                .post(ClientResponse.class, jsonString);
        if (response.getStatus() != 200) {
            throw new RuntimeException(FAILED_HTTP_ERROR_CODE + response.getStatus());
        }
        return response.getEntity(String.class);
    }

    private String deleteJSONStringToServer(String pathParam, String jsonString) throws ClientServiceException {
        WebResource webResource = client.resource(restServiceURL + pathParam);
        webResource.header(HttpHeaders.AUTHORIZATION, "Bearer " + AuthenticationServiceClient.getJwtToken());
        ClientResponse response = webResource.accept("application/json")
                .delete(ClientResponse.class, jsonString);
        if (response.getStatus() != 200) {
            throw new ClientServiceException(FAILED_HTTP_ERROR_CODE + response.getStatus());
        }
        return response.getEntity(String.class);
    }

}