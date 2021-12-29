package org.stroganov.history;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.History;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;

import java.util.List;

public class WEBHistoryManager {

    private static final Logger LOGGER = Logger.getLogger(WEBHistoryManager.class);

    private WEBHistoryManager() {
    }
    public static void saveAction(String userLogin, String stringAction) {
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        User user = libraryDAO.findUser(userLogin);
        if (user != null) {
            History history = new History(user, stringAction);
            libraryDAO.addHistoryEvent(history);
        } else {
            LOGGER.error("Error saving history event - user didn't found");
        }
    }

    public static List<History> getAllHistoryList() {
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        return libraryDAO.getAllHistory();
    }
}
