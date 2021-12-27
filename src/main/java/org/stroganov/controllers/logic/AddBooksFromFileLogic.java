package org.stroganov.controllers.logic;

import org.apache.log4j.Logger;
import org.stroganov.JsonDBAPI.JsonDBLoader;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.BookBuilder;
import org.stroganov.util.DataManager;
import org.stroganov.util.MessageManager;

import java.io.IOException;
import java.util.List;

public class AddBooksFromFileLogic {
    private final Logger logger = Logger.getLogger(AddBooksFromFileLogic.class);

    public String addBookFromFile(String filePath) {
        List<Book> bookList;
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();

        try {
            if (filePath.endsWith(".csv")) {
                BookBuilder bookBuilder = new BookBuilder(libraryDAO);
                bookList = bookBuilder.getBookListFromTXTFile(filePath);
            } else {
                if (filePath.endsWith(".json")) {
                    bookList = JsonDBLoader.loadEntities(filePath, Book.class);
                } else {
                    return MessageManager.getProperty("message.addBooksFromFile.wrongFileFormat");
                }
            }
            libraryDAO.addBook(bookList);
        } catch (IOException | DBExceptions e) {
            logger.error(MessageManager.getProperty("message.addBooksFromFile.errorIO") + e.getMessage());
            return MessageManager.getProperty("message.addBooksFromFile.errorIO") + e.getMessage();
        }
        return MessageManager.getProperty("message.addBooksFromFile.success");
    }
}
