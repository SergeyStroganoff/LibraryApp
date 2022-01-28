package org.stroganov.rs.controller;


import org.stroganov.entities.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/api/book")
public class BookController extends Controller {


    @DELETE
    @Path("{bookISBN}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteBook(@PathParam("bookISBN") String bookISBN) {
        Book book = libraryDAO.findBook(bookISBN);
        boolean operationResult = false;
        if (book != null) {
            try {
                operationResult = libraryDAO.deleteBook(book);
            } catch (IOException e) {
                return Response.status(500)
                        .entity(e.getMessage())
                        .build();
            }
        }
        return Response.status(200)
                .entity(operationResult)
                .build();
    }

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addBook(Book book) {
        boolean operationResult;
        try {
            operationResult = libraryDAO.addBook(book);
        } catch (IOException e) {
            return Response.status(500)
                    .entity(e.getMessage())
                    .build();
        }
        return Response.status(200)
                .entity(operationResult)
                .build();
    }

    @POST
    @Path("/addList")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addBooks(List<Book> bookList) {
        boolean operationResult;
        try {
            operationResult = libraryDAO.addBook(bookList);
        } catch (IOException e) {
            return Response.status(500)
                    .entity(e.getMessage())
                    .build();
        }
        return Response.status(200)
                .entity(operationResult)
                .build();
    }
}
