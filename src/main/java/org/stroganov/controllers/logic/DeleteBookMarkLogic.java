package org.stroganov.controllers.logic;

import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;
import org.stroganov.util.MessageManager;
import org.stroganov.util.StringValidator;

import java.io.IOException;
import java.util.List;

public class DeleteBookMarkLogic {

    public String deleteBookMark(String bookISBN, String bookPageNumber, String userLogin) {
        String message = "";
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        if (!StringValidator.isStringNumberPage(bookPageNumber)) {
            return MessageManager.getProperty("message.deleteBookMark.errorData");
        }
        int pageNumber = Integer.parseInt(bookPageNumber);
        User user = libraryDAO.findUser(userLogin);
        List<BookMark> bookMarks = libraryDAO.findUserBookMarks(user);

        BookMark bookMark = null;
        for (BookMark nextBookMark : bookMarks) {
            if (nextBookMark.getBook().getNumberISBN().equals(bookISBN) && nextBookMark.getBookPageNumber() == pageNumber) {
                bookMark = nextBookMark;
                break;
            }
        }
        if (bookMark == null) {
            return MessageManager.getProperty("message.deleteBookMark.noSuchBookMark");
        }
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
