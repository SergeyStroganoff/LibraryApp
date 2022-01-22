package org.stroganov.controller;

import org.stroganov.entities.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/api/user")
public class UserController extends Controller {

    @GET
    @Path("{userLogin}")
    @Produces({MediaType.APPLICATION_JSON})
    public User findUser(@PathParam("userLogin") String userLogin) {
        return libraryDAO.findUser(userLogin);
    }

    @DELETE
    @Path("{userLogin}")
    public boolean deleteUser(@PathParam("userLogin") String userLogin) throws IOException {
        User user = libraryDAO.findUser(userLogin);
        if (user != null) {
            return libraryDAO.deleteUser(user);
        }
        return false;
    }

    @GET
    @Path("{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser(@PathParam("user") User user) {
        return libraryDAO.findUser(userLogin);
    }

}
