package org.stroganov.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;
import org.stroganov.entities.User;
import org.stroganov.exceptions.AuthenticationException;
import org.stroganov.models.UserDTO;
import org.stroganov.util.PropertiesManager;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AuthenticationServiceClient {

    public static final String FAILED_HTTP_ERROR_CODE = "Failed : HTTP error code : ";
    private static final Logger LOGGER = Logger.getLogger(AuthenticationServiceClient.class);
    public static final String ERROR_GETTING_URL_REST_SERVICE = "Error getting URL RestService";
    public static final String ERROR_JWT_TOKEN_GETTING_SERVER_RESPONSE = "Error jwt token getting. Server response:";
    private static String jwtToken;

    private AuthenticationServiceClient() {
    }

    public static String getJWTToken(User user) throws JsonProcessingException, AuthenticationException {
        if (jwtToken != null) {
            return jwtToken;
        }
        UserDTO userDTO = new UserDTO(user);
        final String restServiceAuthenticationURL = PropertiesManager.getProperties().getProperty("restServiceAuthenticationURL");
        if (restServiceAuthenticationURL == null) {
            LOGGER.error(ERROR_GETTING_URL_REST_SERVICE);
            throw new AuthenticationException(ERROR_GETTING_URL_REST_SERVICE);
        }

        ObjectMapper mapper = new ObjectMapper();
        final Client client = Client.create();
        WebResource webResource = client.resource(restServiceAuthenticationURL);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, mapper.writeValueAsString(userDTO));
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            LOGGER.error(ERROR_JWT_TOKEN_GETTING_SERVER_RESPONSE + response.getStatus());
            throw new AuthenticationException(FAILED_HTTP_ERROR_CODE + response.getStatus());
        }
        jwtToken = response.getEntity(String.class);
        return jwtToken;
    }

    public static String getJwtToken() {
        if (jwtToken != null) {
            return jwtToken;
        } else {
            throw new NullArgumentException("JwtToken is empty!");
        }
    }
}
