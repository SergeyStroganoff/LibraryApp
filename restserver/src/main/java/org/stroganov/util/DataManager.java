package org.stroganov.util;

import org.apache.log4j.Logger;
import org.stroganov.dao.DAOType;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.history.HistoryManager;

import java.io.IOException;

public class DataManager {

    private static LibraryDAO libraryDAO = null;
    private static HistoryManager historyManager = null;
    private static final Logger logger = Logger.getLogger(DataManager.class);

    private static void libraryDAOInitialization() {
        try {
            libraryDAO = DAOType.MYSQL.getLibraryDAO();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static void historyManagerInitialization() {
        try {
            historyManager = new HistoryManager();
        } catch (
                IOException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
    }

    public static LibraryDAO getLibraryDAO() {
        if (libraryDAO == null) {
            libraryDAOInitialization();
        }
        return libraryDAO;
    }

    public static HistoryManager getHistoryManager() {
        if (historyManager == null) {
            historyManagerInitialization();
        }
        return historyManager;
    }
}
