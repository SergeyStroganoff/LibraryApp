package org.stroganov.dao;

import org.stroganov.exceptions.DBExceptions;

public class DAOFactory {

    private DAOFactory() {
    }

    public static LibraryDAO getLibraryDAO(DAOType type) throws DBExceptions {
        return type.getLibraryDAO();
    }
}


