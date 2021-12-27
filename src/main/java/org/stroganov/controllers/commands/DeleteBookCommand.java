package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.DeleteBookLogic;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBookCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.deleteBook");
        String bookISBN = request.getParameter("inputBookISBN");
        request.setAttribute("status", "executed");
        request.setAttribute("messageTitle", MessageManager.getProperty("message.deleteBook.title"));

        DeleteBookLogic deleteBookLogic = new DeleteBookLogic();
        String resultMessage = deleteBookLogic.deleteBook(bookISBN);
        request.setAttribute(RESULT_MESSAGE, resultMessage);
        return page;
    }
}
