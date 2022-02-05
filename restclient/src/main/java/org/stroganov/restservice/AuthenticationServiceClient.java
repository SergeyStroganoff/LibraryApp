package org.stroganov.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.stroganov.entities.User;
import org.stroganov.util.PropertiesManager;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AuthenticationServiceClient {

    public static final String FAILED_HTTP_ERROR_CODE = "Failed : HTTP error code : ";
    private static final Logger LOGGER = Logger.getLogger(AuthenticationServiceClient.class);
    public static final String ERROR_GETTING_URL_REST_SERVICE = "Error getting URL RestService";

    private static String jwtToken;


    public static String getJWTToken(User user) throws JsonProcessingException {
        if (jwtToken != null) {
            return jwtToken;
        }
        final String restServiceAuthenticationURL = PropertiesManager.getProperties().getProperty("restServiceAuthenticationURL");
        if (restServiceAuthenticationURL==null){
            LOGGER.error(ERROR_GETTING_URL_REST_SERVICE);
            throw new RuntimeException(ERROR_GETTING_URL_REST_SERVICE);
        }

        ObjectMapper mapper = new ObjectMapper();
        final Client client = Client.create();
        WebResource webResource = client.resource(restServiceAuthenticationURL);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, mapper.writeValueAsString(user));
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            LOGGER.error("Error jwt token getting. Server response:" + response.getStatus());
            throw new RuntimeException(FAILED_HTTP_ERROR_CODE + response.getStatus());
        }
        jwtToken = response.getEntity(String.class);
        return jwtToken;
    }

    public static String getJwtToken() {
        if (jwtToken != null) {
            return jwtToken;
        } else {
            throw new NullArgumentException("JWTToken empty!");
        }
    }
}
