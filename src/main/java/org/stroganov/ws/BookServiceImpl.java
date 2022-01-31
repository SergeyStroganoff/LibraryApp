package org.stroganov.ws;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.util.DataManager;

import java.io.IOException;
import java.util.List;

@HandlerChain(file = "../userHandler.xml")
@WebService(endpointInterface = "org.stroganov.ws.BookService")
public class BookServiceImpl implements BookService {

    private final LibraryDAO libraryDAO = DataManager.getLibraryDAO();

    @Override
    public boolean addBook(Book book) throws IOException {
        return libraryDAO.addBook(book);
    }

    @Override
    public boolean addBookList(Book[] bookArray) throws IOException {
        List<Book> bookList = List.of(bookArray);
        return libraryDAO.addBooks(bookList);
    }

    @Override
    public boolean deleteBook(Book book) throws IOException {
        return libraryDAO.deleteBook(book);
    }

    @Override
    public boolean addAuthor(Author author) throws IOException {
        return libraryDAO.addAuthor(author);
    }
}



