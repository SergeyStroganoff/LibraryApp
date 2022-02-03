package org.stroganov.rs.controller;

import org.stroganov.entities.User;
import org.stroganov.rs.filter.AdminStatusNeeded;
import org.stroganov.rs.filter.JWTTokenNeeded;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/api/user")
public class UserController extends Controller {

    @GET
    @Path("/{userLogin}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public User findUser(@PathParam("userLogin") String userLogin) {
        return libraryDAO.findUser(userLogin);
    }

    @DELETE
    @Path("/{userLogin}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    @AdminStatusNeeded
    public Response deleteUser(@PathParam("userLogin") String userLogin) {
        boolean operationResult = false;
        User user = libraryDAO.findUser(userLogin);
        if (user != null) {
            try {
                operationResult = libraryDAO.deleteUser(user);
            } catch (IOException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(e.getMessage())
                        .build();
            }
        }
        return Response.status(Response.Status.OK)
                .entity(operationResult)
                .build();

    }

    @POST
    @Path("/block/{userLogin}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    @AdminStatusNeeded
    public Response blockUser(@PathParam("userLogin") String userLogin) {
        boolean operationResult = false;
        User user = libraryDAO.findUser(userLogin);
        if (user != null) {
            try {
                operationResult = libraryDAO.blockUser(user);
            } catch (IOException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(e.getMessage())
                        .build();
            }
        }
        return Response.status(Response.Status.OK)
                .entity(operationResult)
                .build();
    }

    @POST
    @Path("/unblock/{userLogin}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    @AdminStatusNeeded
    public Response unblockUser(@PathParam("userLogin") String userLogin) {
        boolean operationResult = false;
        User user = libraryDAO.findUser(userLogin);
        if (user != null) {
            try {
                operationResult = libraryDAO.unblockUser(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Response.status(Response.Status.OK)
                .entity(operationResult)
                .build();
    }

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    @AdminStatusNeeded
    public Response addUser(User user) throws IOException {
        boolean operationResult = libraryDAO.addUser(user);
        return Response.status(Response.Status.OK)
                .entity(operationResult)
                .build();
    }
}
