package org.stroganov.rs.controller;

import org.stroganov.entities.Author;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/api/history")
public class AuthorController extends Controller {

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addAuthor(Author author) throws IOException {
        boolean operationResult = libraryDAO.addAuthor(author);
        return Response.status(200)
                .entity(operationResult)
                .build();
    }

    @GET
    @Path("{authorName}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAuthor(@PathParam("authorName") String authorName) {
        Author author = libraryDAO.findAuthor(authorName);
        return Response.status(200)
                .entity(author)
                .build();
    }

    @DELETE
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteAuthorWithAllHisBooks(Author author) throws IOException {
        boolean operationResult = libraryDAO.deleteAuthorWithAllHisBooks(author);
        return Response.status(200)
                .entity(operationResult)
                .build();
    }
}
