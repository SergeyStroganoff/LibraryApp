package org.stroganov.rs.controller;

import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.rs.filter.JWTTokenNeeded;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Path("/api/bookMark")
public class BookMarkController extends Controller {

    @DELETE
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    public Response deleteBookMark(@PathParam("id") int id) {
        boolean operationResult = false;
        BookMark bookMark = libraryDAO.findBookMarkById(id);
        if (bookMark == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(operationResult)
                    .build();
        }
        try {
            operationResult = libraryDAO.deleteBookMark(bookMark);
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
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
    public Response addBookMark(BookMark bookMark) {
        boolean operationResult;
        try {
            operationResult = libraryDAO.addBookMark(bookMark);
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
        return Response.status(Response.Status.OK)
                .entity(operationResult)
                .build();
    }

    @GET
    @Path("/{userLogin}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    public Response findUserBookMarks(@PathParam("userLogin") String userLogin) {
        User user = libraryDAO.findUser(userLogin);
        List<BookMark> bookMarkList = Collections.emptyList();
        if (user != null) {
            bookMarkList = libraryDAO.findUserBookMarks(user);
        }
        return Response.status(Response.Status.OK)
                .entity(bookMarkList)
                .build();
    }
}
