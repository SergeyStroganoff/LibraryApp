package org.stroganov.dao;

import org.hibernate.SessionFactory;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.HibernateUtil;

import java.net.MalformedURLException;

public class DAOFactory {

    private static final String ERROR_PARAMETER_MESSAGE = "Тип подключения к базе указан неверно";

    private DAOFactory() {
    }

    public static LibraryDAO getLibraryDAO(DAOType type) throws DBExceptions {
        LibraryDAO dao;
        switch (type) {
            case JSON:
                dao = JsonLibraryDAO.getInstance();
                break;
            case WEB_SERVICE_SOAP:
                try {
                    dao = SoapServiceLibraryDAO.getInstance();
                } catch (MalformedURLException e) {
                    throw new DBExceptions("SoapServiceLibraryDAO exception was thrown:", e);
                }
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


