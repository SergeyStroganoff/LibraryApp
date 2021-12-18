package org.stroganov.servlets.actions;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
   String execute(HttpServletRequest servletRequest);
}
