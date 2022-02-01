package org.stroganov.rs.controller;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.util.DataManager;

public abstract class Controller {
    protected final LibraryDAO libraryDAO = DataManager.getLibraryDAO();
    protected final Logger logger = Logger.getLogger(Controller.class);

}
