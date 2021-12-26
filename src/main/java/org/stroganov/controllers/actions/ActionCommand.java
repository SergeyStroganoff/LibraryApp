package org.stroganov.controllers.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {

    String RESULT_MESSAGE = "resultMessage";

    String execute(HttpServletRequest request, HttpServletResponse response);
}
