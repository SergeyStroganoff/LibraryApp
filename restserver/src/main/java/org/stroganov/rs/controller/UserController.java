package org.stroganov.rs.controller;

import org.stroganov.entities.User;
import org.stroganov.models.UserDTO;
import org.stroganov.rs.filter.AdminStatusNeeded;
import org.stroganov.rs.filter.JWTTokenNeeded;
import org.stroganov.util.TransitionObjectsService;

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
    public UserDTO findUser(@PathParam("userLogin") String userLogin) {
        User user = libraryDAO.findUser(userLogin);
        logger.info("Have got user from DB: " + user);
        return new UserDTO(user);
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
                logger.error(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(IO_EXCEPTION_IN_LIBRARY_DAO_MESSAGE)
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
                logger.error(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(IO_EXCEPTION_IN_LIBRARY_DAO_MESSAGE)
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
                logger.error(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(IO_EXCEPTION_IN_LIBRARY_DAO_MESSAGE)
                        .build();
            }
        }
        return Response.ok()
                .entity(operationResult)
                .build();
    }

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    @AdminStatusNeeded
    public Response addUser(UserDTO userDTO) throws IOException {
        User user = TransitionObjectsService.getUser(userDTO);
        boolean operationResult = libraryDAO.addUser(user);
        return Response.status(Response.Status.OK)
                .entity(operationResult)
                .build();
    }
}
