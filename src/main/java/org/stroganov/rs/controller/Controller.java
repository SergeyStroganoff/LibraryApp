package org.stroganov.rs.controller;

import org.stroganov.dao.LibraryDAO;
import org.stroganov.util.DataManager;

public abstract class Controller {
    LibraryDAO libraryDAO = DataManager.getLibraryDAO();
}
