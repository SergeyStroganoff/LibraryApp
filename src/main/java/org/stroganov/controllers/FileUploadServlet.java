package org.stroganov.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.stroganov.controllers.logic.AddBooksFromFileLogic;
import org.stroganov.util.ConfigurationManager;
import org.stroganov.util.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to handle File upload request from Client
 *
 * @author Sergey Stroganov
 */
@WebServlet("/fileloader")
public class FileUploadServlet extends HttpServlet {
    private final String DIRECTORY = ConfigurationManager.getProperties("serverUploadFolder");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resultMessage = "resultMessage";
        String fullFilePath = null;
        //process only if its multipart content
        request.setAttribute("status", "executed");
        request.setAttribute("messageTitle", MessageManager.getProperty("message.addNewAuthorMessage.title"));

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multipart = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multipart) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        String path = new File(".").getCanonicalPath();
                        fullFilePath = path + DIRECTORY + File.separator + name;
                        item.write(new File(fullFilePath));
                    }
                }
                //File uploaded successfully
                String message;
                if (fullFilePath != null) {
                    AddBooksFromFileLogic addBooksFromFileLogic = new AddBooksFromFileLogic();
                    message = addBooksFromFileLogic.addBookFromFile(fullFilePath);
                } else {
                    message = "Ошибка - файл не был инициализирован";
                }
                request.setAttribute(resultMessage, message);

            } catch (Exception ex) {
                request.setAttribute(resultMessage, "Ошибка. Файл не загружен: " + ex.getMessage() + fullFilePath);
            }
        } else {
            request.setAttribute(resultMessage, "Sorry this Servlet only handles file upload request");
        }
        String page = ConfigurationManager.getProperties("path.page.addBooksFromFile");
        request.getRequestDispatcher(page).forward(request, response);
    }

}
