package org.stroganov.controllers.logic;

import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.util.BookBuilder;
import org.stroganov.util.DataManager;

import java.io.IOException;

public class AddBookLogic {

    public boolean addNewBook(String numberISBN, String bookName, String authorName,
                              String publishingYear, String pagesNumber) throws IOException {

        String[] booksParameters = {numberISBN, bookName, authorName, publishingYear, pagesNumber};
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        BookBuilder bookBuilder = new BookBuilder(libraryDAO);
        Book book = bookBuilder.buildBook(booksParameters);
        return libraryDAO.addBook(book);
    }
}
