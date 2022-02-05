package org.stroganov.rs.controller;

import org.stroganov.entities.Author;
import org.stroganov.models.AuthorDTO;
import org.stroganov.rs.filter.JWTTokenNeeded;
import org.stroganov.util.TransitionObjectsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/api/author")
public class AuthorController extends Controller {

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    public Response addAuthor(AuthorDTO authorDTO) throws IOException {
        boolean operationResult = libraryDAO.addAuthor(TransitionObjectsService.getAuthor(authorDTO));
        logger.info("Result of addAuthor: " + operationResult);
        return Response.status(Response.Status.OK)
                .entity(operationResult)
                .build();
    }

    @GET
    @Path("/{authorName}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    public Response findAuthor(@PathParam("authorName") String authorName) {
        Author author = libraryDAO.findAuthor(authorName);
        return Response.status(Response.Status.OK)
                .entity(author)
                .build();
    }

    @DELETE
    @Path("/{authorName}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    public Response deleteAuthorWithAllHisBooks(@PathParam("authorName") String authorName) throws IOException {
        Author author = libraryDAO.findAuthor(authorName);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(authorName + " not found")
                    .build();
        }
        boolean operationResult = libraryDAO.deleteAuthorWithAllHisBooks(author);
        logger.info("Result of deleteAuthorWithAllHisBooks: " + operationResult);
        return Response.status(Response.Status.OK)
                .entity(operationResult)
                .build();
    }
}
