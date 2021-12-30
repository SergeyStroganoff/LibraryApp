package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.DeleteBookMarkLogic;
import org.stroganov.history.WEBHistoryManager;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBookMarkCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.deleteBookMark");
        String bookISBN = request.getParameter("inputBookISBN");
        String bookPageNumber = request.getParameter("inputBookPage");
        request.setAttribute("status", "executed");
        request.setAttribute("messageTitle", MessageManager.getProperty("message.deleteBookMark.title"));
        String userLogin = (String) request.getSession().getAttribute("userLogin");
        DeleteBookMarkLogic deleteBookMarkLogic = new DeleteBookMarkLogic();
        String resultMessage = deleteBookMarkLogic.deleteBookMark(bookISBN, bookPageNumber, userLogin);
        request.setAttribute(RESULT_MESSAGE, resultMessage);
        WEBHistoryManager.saveAction((String) request.getSession().getAttribute("userLogin"), resultMessage);
        return page;
    }
}
