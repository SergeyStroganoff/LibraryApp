package org.stroganov.rs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.stroganov.entities.User;
import org.stroganov.rs.model.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;

@Path("/api")
public class AuthenticationController extends Controller {

    @POST
    @Path("/authentication")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    // QueryParam
    public Response authentication(User user) {

        User ourUser = libraryDAO.findUser(user.getLogin());

        if (!ourUser.getPasscodeHash().equals(user.getPasscodeHash())) { // todo
            return Response.status(401)
                    .entity("Password is not correct: " + user.getLogin())
                    .build();
        }
        String newJWTToken = createJWTToken(ourUser);
        final ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("token", newJWTToken);
        return Response.status(Response.Status.OK).entity(json).build();
    }

    private String createJWTToken(User user) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder()
                .setIssuer("LibraryServer")
                .setSubject(user.getLogin())
                .claim("admin", user.isAdmin())
                .signWith(key)
                .compact();
    }

}
