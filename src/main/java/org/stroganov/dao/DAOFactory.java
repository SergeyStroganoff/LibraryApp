package org.stroganov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.HibernateUtil;

public class DAOFactory {

    private static final String ERROR_PARAMETER_MESSAGE = "Тип подключения к базе указан неверно";


    public static LibraryDAO getLibraryDAO(DAOType type) throws DBExceptions {
        LibraryDAO dao;
        switch (type) {
            case JSON:
                dao = JsonLibraryDAO.getInstance();
                break;
            case MYSQL:
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                dao = MySQLLibraryDAO.getInstance(sessionFactory);
                break;
            default:
                throw new IllegalArgumentException(ERROR_PARAMETER_MESSAGE);
        }
        return dao;
    }

}


