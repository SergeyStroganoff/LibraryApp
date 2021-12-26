package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.AddUserLogic;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.addUser");
        String userName = request.getParameter("inputFullName");
        String userLogin = request.getParameter("inputLogin");
        String userPassword = request.getParameter("inputPassword");
        String adminStatus = request.getParameter("inputAdminStatusBox");
        request.setAttribute("status", "executed");
        request.setAttribute("messageTitle", MessageManager.getProperty("message.addUserMessage.title"));
        if (userLogin == null || userLogin.equals("")) {
            request.setAttribute(RESULT_MESSAGE, MessageManager.getProperty("message.addUserMessage.userDataError"));
        }
        AddUserLogic addUserLogic = new AddUserLogic();
        String resultMessage = addUserLogic.addNewUser(userName, userLogin, userPassword, adminStatus);
        request.setAttribute(RESULT_MESSAGE, resultMessage);
        return page;
    }
}
