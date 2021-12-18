package org.stroganov.controllers;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.actions.ActionFactory;
import org.stroganov.util.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class LibraryServlet extends HttpServlet {
    public static final String ERROR_CONFIGURATION_LOADING = "Error configuration loading";
    private final ServletConfig servletConfig = this.getServletConfig();

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
        String pagePath = null;
// определение команды, пришедшей из JSP
        ActionCommand command = ActionFactory.defineCommand(request);
        /* вызов реализованного метода execute() и передача параметров
         * классу-обработчику конкретной команды*/
        pagePath = command.execute(request);
// метод возвращает страницу ответа
// page = null; // поэксперементировать!
        if (pagePath != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
// вызов страницы ответа на запрос
            dispatcher.forward(request, response);
        } else {
// установка страницы c cообщением об ошибке
            pagePath = ConfigurationManager.getProperties("path.page.index");
            request.getSession().setAttribute("nullPage", "Server Error. Empty page was received");
            response.sendRedirect(request.getContextPath() + pagePath);
        }
    }
}

//  request.setAttribute("name", this.getServletContext().getServletContextName());
//  request.getRequestDispatcher("login.jsp").forward(request, response);
