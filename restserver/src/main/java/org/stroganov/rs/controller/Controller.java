package org.stroganov.rs.controller;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.util.DataManager;

public abstract class Controller {
    protected static final String IO_EXCEPTION_IN_LIBRARY_DAO_MESSAGE = "IO Exception in libraryDAO, see logFile on Server";
    protected final LibraryDAO libraryDAO = DataManager.getLibraryDAO();
    protected final Logger logger = Logger.getLogger(Controller.class);

}
