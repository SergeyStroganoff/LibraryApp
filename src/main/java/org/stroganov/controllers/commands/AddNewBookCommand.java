package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.AddBookLogic;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewBookCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.addBook");
        String ISBN = request.getParameter("ISBN");
        String bookName = request.getParameter("bookName");
        String bookAuthorName = request.getParameter("authorName");
        String bookPublishingYear = request.getParameter("publishingYear");
        String bookPagesNumber = request.getParameter("pagesNumber");
        AddBookLogic addBookLogic = new AddBookLogic();
        request.setAttribute("status", "added");
        boolean wasBookAdd;
        try {
            wasBookAdd = addBookLogic.addNewBook(ISBN, bookName, bookAuthorName, bookPublishingYear, bookPagesNumber);
        } catch (IOException e) {
            request.setAttribute("resultMessage",
                    MessageManager.getProperty("message.addNewBookMessageError") + e.getMessage());
            return page;
        }

        if (wasBookAdd) {
            request.setAttribute("resultMessage", MessageManager.getProperty("message.addNewBookMessageSuccessful"));
        } else {
            request.setAttribute("resultMessage", MessageManager.getProperty("message.addNewBookMessageNotSuccessful"));
        }
        return page;
    }
}
