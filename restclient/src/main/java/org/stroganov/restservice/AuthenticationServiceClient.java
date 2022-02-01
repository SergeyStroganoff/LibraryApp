package org.stroganov.restservice;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;
import org.stroganov.entities.User;
import org.stroganov.util.PropertiesManager;

public class AuthenticationServiceClient {

    public static final String FAILED_HTTP_ERROR_CODE = "Failed : HTTP error code : ";
    private final Logger logger = Logger.getLogger(AuthenticationServiceClient.class);
    private static final Client client = Client.create();
    private static final Gson gson = new Gson();
    private static final String REST_SERVICE_AUTHENTICATION_URL = PropertiesManager.getProperties().getProperty("restServiceAuthenticationURL");
    private static String JWTToken;


    public static String getJWTToken(User user) {
        if (JWTToken != null) {
            return JWTToken;
        }
       // String jsonString = gson.toJson(user, User.class);
        WebResource webResource = client.resource(REST_SERVICE_AUTHENTICATION_URL);
        ClientResponse response = webResource.accept("application/json")
                .post(ClientResponse.class, user);
        if (response.getStatus() != 200) {
            throw new RuntimeException(FAILED_HTTP_ERROR_CODE + response.getStatus());
        }
        JWTToken = response.getEntity(String.class);
        return JWTToken;
    }

    public static String getJWTToken() {
        if (JWTToken != null) {
            return JWTToken;
        } else {
            throw new NullArgumentException("JWTToken empty!");
        }
    }
}
