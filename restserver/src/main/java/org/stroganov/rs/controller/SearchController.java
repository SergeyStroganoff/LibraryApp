package org.stroganov.rs.controller;

import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.rs.filter.JWTTokenNeeded;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/api/search")
@JWTTokenNeeded
public class SearchController extends Controller {

    @GET
    @Path("/{numberISBN}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findBook(@PathParam("numberISBN") String numberISBN) {
        Book book = libraryDAO.findBook(numberISBN);
        return Response.status(200)
                .entity(book)
                .build();
    }

    @GET
    @Path("/partOfName/{partOfName}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findUserBookMarks(@PathParam("partOfName") String partOfName) {
        List<Book> bookList = libraryDAO.findBooksByPartName(partOfName);
        return Response.status(200)
                .entity(bookList)
                .build();
    }

    @GET
    @Path("/partOfAuthorName/{partAuthorName}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findBooksByPartAuthorName(@PathParam("partAuthorName") String partOfName) {
        List<Book> bookList = libraryDAO.findBooksByPartAuthorName(partOfName);
        return Response.status(200)
                .entity(bookList)
                .build();
    }


    @GET
    @Path("/findBooksByYearsRange")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findUserBookMarks(@QueryParam("firstYear") int firstYear, @QueryParam("secondYear") int secondYear) {
        List<Book> bookList = libraryDAO.findBooksByYearsRange(firstYear, secondYear);
        return Response.status(200)
                .entity(bookList)
                .build();
    }

    @GET
    @Path("/findBooksByParameters")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findBooksByParameters(@QueryParam("bookYear") int bookYear, @QueryParam("bookPages") int bookPages, @QueryParam("partBookName") String partBookName) {
        List<Book> bookList = libraryDAO.findBooksByParameters(bookYear, bookPages, partBookName);
        return Response.status(200)
                .entity(bookList)
                .build();
    }

    @GET
    @Path("/findBooksWithUserBookMarks/{userLogin}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findBooksWithUserBookMarks(@PathParam("userLogin") String userLogin) {
        User user = libraryDAO.findUser(userLogin);
        List<Book> bookList = Collections.emptyList();
        if (user != null) {
            bookList = libraryDAO.findBooksWithUserBookMarks(user);
        }
        return Response.status(200)
                .entity(bookList)
                .build();
    }
}
