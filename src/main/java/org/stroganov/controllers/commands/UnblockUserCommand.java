package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.ChangeUserStatusLogic;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.unblockUser");
        String userLogin = request.getParameter("inputUserLogin");
        request.setAttribute("status", "executed");
        request.setAttribute("messageTitle", MessageManager.getProperty("message.unblockUser.title"));

        String currentUserRole = (String) request.getSession().getAttribute("role");
        if (!currentUserRole.equals("admin")) {
            request.setAttribute(RESULT_MESSAGE, MessageManager.getProperty("message.wrongRole"));
            return page;
        }

        ChangeUserStatusLogic changeUserStatusLogic = new ChangeUserStatusLogic();
        String resultMessage = changeUserStatusLogic.changeUserStatus(userLogin, "unblock");
        request.setAttribute(RESULT_MESSAGE, resultMessage);
        return page;
    }
}
