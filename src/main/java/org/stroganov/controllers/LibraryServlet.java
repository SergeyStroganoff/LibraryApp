package org.stroganov.controllers;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.actions.ActionFactory;
import org.stroganov.util.ConfigurationManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class LibraryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagePath;
        ActionCommand command = ActionFactory.defineCommand(request);
        pagePath = command.execute(request, response);
        if (pagePath != null) {
// вызов страницы ответа на запрос
            request.getRequestDispatcher(pagePath).forward(request, response);
        } else {
// установка страницы об ошибке
            pagePath = ConfigurationManager.getProperties("path.page.index");
            request.getSession().setAttribute("nullPage", "Server Error. Empty page was received");
            response.sendRedirect(request.getContextPath() + pagePath);
        }
    }
}

