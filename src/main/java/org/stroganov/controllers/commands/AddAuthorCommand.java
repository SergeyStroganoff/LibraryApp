package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.AddAuthorLogic;
import org.stroganov.history.WEBHistoryManager;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAuthorCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.addAuthor");
        String authorName = request.getParameter("inputAuthorName");
        request.setAttribute("status", "executed");
        request.setAttribute("messageTitle", MessageManager.getProperty("message.addNewAuthorMessage.title"));
        AddAuthorLogic addAuthorLogic = new AddAuthorLogic();
        String resultMessage = addAuthorLogic.addNewAuthor(authorName);
        request.setAttribute(RESULT_MESSAGE, resultMessage);
        WEBHistoryManager.saveAction((String) request.getSession().getAttribute("userLogin"), resultMessage);
        return page;
    }
}
