package org.stroganov.rs.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.log4j.Logger;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    final Logger logger = Logger.getLogger(JWTTokenNeededFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("KeyBearer".length()).trim();
        Jws<Claims> jws;
        try {
            // Validate the token
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            jws = Jwts.parserBuilder()  // (1)
                    .setSigningKey(key)         // (2)
                    .build()                    // (3)
                    .parseClaimsJws(token); // (4)

            logger.info("Token contains the subject : " + jws.getBody().getSubject());
            System.out.println("Token contains the subject : " + jws.getBody().getSubject());
            // we can safely trust the JWT
            // Jwts.parser().setSigningKey(key).parseClaimsJws(token); Old realization
            logger.info("Server got the valid token : " + token);

        } catch (Exception e) {
            logger.error("Server got the invalid token : " + token);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
