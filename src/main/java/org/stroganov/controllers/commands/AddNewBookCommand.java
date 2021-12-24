package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.AddBookLogic;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;
import org.stroganov.util.StringValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewBookCommand implements ActionCommand {
    public static final String RESULT_MESSAGE = "resultMessage";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.addBook");
        String ISBN = request.getParameter("ISBN");
        String bookName = request.getParameter("bookName");
        String bookAuthorName = request.getParameter("authorName");
        String bookPublishingYear = request.getParameter("publishingYear");
        String bookPagesNumber = request.getParameter("pagesNumber");
        AddBookLogic addBookLogic = new AddBookLogic();
        request.setAttribute("status", "executed");
        request.setAttribute("messageTitle", MessageManager.getProperty("message.addNewBookMessage.title"));

        if (!StringValidator.isStringYear(bookPublishingYear) || !StringValidator.isStringNumberPage(bookPagesNumber)) {
            request.setAttribute(RESULT_MESSAGE, MessageManager.getProperty("message.addNewBookMessage.wrongInputData"));
            return page;
        }

        boolean wasBookAdd;
        try {
            wasBookAdd = addBookLogic.addNewBook(ISBN, bookName, bookAuthorName, bookPublishingYear, bookPagesNumber);
        } catch (IOException e) {
            request.setAttribute(RESULT_MESSAGE,
                    MessageManager.getProperty("message.addNewBookMessage.error") + e.getMessage());
            return page;
        }

        if (wasBookAdd) {
            request.setAttribute(RESULT_MESSAGE, MessageManager.getProperty("message.addNewBookMessage.successful"));
        } else {
            request.setAttribute(RESULT_MESSAGE, MessageManager.getProperty("message.addNewBookMessage.notSuccessful"));
        }
        return page;
    }
}
