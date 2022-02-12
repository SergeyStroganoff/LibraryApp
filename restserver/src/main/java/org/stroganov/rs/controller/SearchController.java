package org.stroganov.rs.controller;

import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.models.BookDTO;
import org.stroganov.rs.filter.JWTTokenNeeded;
import org.stroganov.util.TransitionObjectsService;

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
        return Response.status(Response.Status.OK)
                .entity(new BookDTO(book))
                .build();
    }

    @GET
    @Path("/partOfName/{partOfName}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findUserBookMarks(@PathParam("partOfName") String partOfName) {
        List<Book> bookList = libraryDAO.findBooksByPartName(partOfName);
        List<BookDTO> bookDTOList = TransitionObjectsService.getBookDTOList(bookList);
        return Response.status(Response.Status.OK)
                .entity(bookDTOList)
                .build();
    }

    @GET
    @Path("/partOfAuthorName/{partAuthorName}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findBooksByPartAuthorName(@PathParam("partAuthorName") String partOfName) {
        List<Book> bookList = libraryDAO.findBooksByPartAuthorName(partOfName);
        List<BookDTO> bookDTOList = TransitionObjectsService.getBookDTOList(bookList);
        return Response.status(Response.Status.OK)
                .entity(bookDTOList)
                .build();
    }


    @GET
    @Path("/findBooksByYearsRange")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findBooksByYearsRange(@QueryParam("firstYear") int firstYear, @QueryParam("secondYear") int secondYear) {
        List<Book> bookList = libraryDAO.findBooksByYearsRange(firstYear, secondYear);
        List<BookDTO> bookDTOList = TransitionObjectsService.getBookDTOList(bookList);
        return Response.status(Response.Status.OK)
                .entity(bookDTOList)
                .build();
    }

    @GET
    @Path("/findBooksByParameters")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response findBooksByParameters(@QueryParam("bookYear") int bookYear, @QueryParam("bookPages") int bookPages, @QueryParam("partBookName") String partBookName) {
        List<Book> bookList = libraryDAO.findBooksByParameters(bookYear, bookPages, partBookName);
        List<BookDTO> bookDTOList = TransitionObjectsService.getBookDTOList(bookList);
        return Response.status(Response.Status.OK)
                .entity(bookDTOList)
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
        List<BookDTO> bookDTOList = TransitionObjectsService.getBookDTOList(bookList);
        return Response.status(Response.Status.OK)
                .entity(bookDTOList)
                .build();
    }
}
