package org.stroganov.dao;

import org.stroganov.exceptions.DBExceptions;

public class DAOFactory {

    private static final String REALIZATION_EXCEPTION_MESSAGE = "Взаимодействие с БД H2 - не реализовано";
    private static final String ERROR_PARAMETER_MESSAGE = "Тип подключения к базе указан неверно";


    public static LibraryDAO getLibraryDAO(DAOType type) throws DBExceptions {
        LibraryDAO dao = null;
        switch (type) {
            case JSON:
                dao = JsonLibraryDAO.getInstance();
                break;
            case H2DATABASE:
                throw new IllegalArgumentException(REALIZATION_EXCEPTION_MESSAGE);
            default:
                throw new IllegalArgumentException(ERROR_PARAMETER_MESSAGE);
        }
        return dao;
    }

}


