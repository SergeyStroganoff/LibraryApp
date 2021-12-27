package org.stroganov.controllers.logic;

import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;
import org.stroganov.util.MessageManager;
import org.stroganov.util.StringValidator;

import java.io.IOException;

public class DeleteBookMarkLogic {

    public String deleteBookMark(String bookISBN, String bookPageNumber, String userLogin) {
        String message = "";
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        if (!StringValidator.isStringNumberPage(bookPageNumber)) {
            return MessageManager.getProperty("message.deleteBookMark.errorData");
        }
        int pageNumber = Integer.parseInt(bookPageNumber);
        Book book = libraryDAO.findBook(bookISBN);
        if (book == null) {
            return MessageManager.getProperty("message.deleteBook.noSuchBook");
        }
        if (book.getPagesNumber() < pageNumber) {
            return MessageManager.getProperty("message.deleteBookMark.noSuchPage");
        }
        User user = libraryDAO.findUser(userLogin);
        BookMark bookMark = new BookMark(book, user, pageNumber);
        try {
            if (libraryDAO.deleteBookMark(bookMark)) {
                message = MessageManager.getProperty("message.deleteBookMark.success");
            } else {
                MessageManager.getProperty("message.deleteBookMark.errorDeleting");
            }
        } catch (IOException e) {
            message = MessageManager.getProperty("message.deleteBookMark.errorDeleting") + " " + e.getMessage();
        }
        return message;
    }
}
