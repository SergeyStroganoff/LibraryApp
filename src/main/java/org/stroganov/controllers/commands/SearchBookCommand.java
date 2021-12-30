package org.stroganov.controllers.commands;

import org.stroganov.controllers.actions.ActionCommand;
import org.stroganov.controllers.logic.SearchBooksLogic;
import org.stroganov.entities.Book;
import org.stroganov.history.WEBHistoryManager;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchBookCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperties("path.page.showBooksList");

        String ISBN = request.getParameter("ISBN");
        String bookName = request.getParameter("bookName");
        String bookAuthorName = request.getParameter("authorName");
        String bookPublishingYear = request.getParameter("publishingYear");
        String bookPublishingYearSecond = request.getParameter("publishingYearSecond");
        String bookPagesNumber = request.getParameter("pagesNumber");
        String pagePath = request.getParameter("pagePath");

        SearchBooksLogic searchBooksLogic = new SearchBooksLogic();
        List<Book> bookList = searchBooksLogic.searchBooksByParameters(ISBN, bookName, bookAuthorName,
                bookPublishingYear, bookPublishingYearSecond, bookPagesNumber);
        WEBHistoryManager.saveAction((String) request.getSession().getAttribute("userLogin"), "Осуществлен поиск книг");

        if (bookList.isEmpty()) {
            request.setAttribute("status", "executed");
            request.setAttribute("messageTitle", MessageManager.getProperty("message.searchBooksMessage.title"));
            request.setAttribute(RESULT_MESSAGE, MessageManager.getProperty("message.searchBooksMessage.noBooks"));
            return pagePath;
        }
        request.setAttribute("books", bookList);
        return page;
    }
}
