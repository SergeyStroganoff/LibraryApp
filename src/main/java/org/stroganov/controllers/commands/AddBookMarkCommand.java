package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.AddBookMarkLogic;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;
import org.stroganov.util.StringValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBookMarkCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.addBookMark");
        String ISBN = request.getParameter("ISBN");
        String bookMarkPageNumber = request.getParameter("pagesNumber");
        request.setAttribute("status", "executed");
        request.setAttribute("messageTitle", MessageManager.getProperty("message.addNewBookMarkMessage.title"));
        if (!StringValidator.isStringNumberPage(bookMarkPageNumber)) {
            request.setAttribute(RESULT_MESSAGE, MessageManager.getProperty("message.addNewBookMessage.wrongInputData"));
            return page;
        }
        AddBookMarkLogic addBookMarkLogic = new AddBookMarkLogic();
        String resultMessage = addBookMarkLogic.addNewBookMark(ISBN, (String) request.getSession().getAttribute("userLogin"), bookMarkPageNumber);
        request.setAttribute(RESULT_MESSAGE, resultMessage);
        return page;
    }
}
