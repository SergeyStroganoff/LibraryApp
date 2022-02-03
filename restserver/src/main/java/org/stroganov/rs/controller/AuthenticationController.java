package org.stroganov.rs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.stroganov.entities.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Path("/api")
public class AuthenticationController extends Controller {

    public static final String BEARER = "Bearer";
    public static final String LIBRARY_SERVER = "LibraryServer";
    public static final String ADMIN = "admin";
    public static final long EXPIRATION_TIME = 45L;

    @POST
    @Path("/authentication")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    // QueryParam
    public Response authentication(User user) {

        User ourUser = libraryDAO.findUser(user.getLogin());
        if (!ourUser.getPasscodeHash().equals(user.getPasscodeHash())) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Password is not correct: " + user.getLogin())
                    .build();
        }
        String newJWTToken = createJWTToken(ourUser);
        final ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put(BEARER, newJWTToken);
        return Response.status(Response.Status.OK)
                .entity(json.toString())
                .build();
    }

    private String createJWTToken(User user) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder()
                .setIssuer(LIBRARY_SERVER)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(EXPIRATION_TIME).atZone(ZoneId.systemDefault()).toInstant()))
                .setSubject(user.getLogin())
                .claim(ADMIN, user.isAdmin())
                .signWith(key)
                .compact();
    }
}
