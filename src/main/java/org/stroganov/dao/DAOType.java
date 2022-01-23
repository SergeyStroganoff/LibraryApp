package org.stroganov.dao;

import org.hibernate.SessionFactory;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.HibernateUtil;

import java.net.MalformedURLException;

public enum DAOType {

    JSON() {
        LibraryDAO getLibraryDAO() throws DBExceptions {
            return JsonLibraryDAO.getInstance();
        }
    },
    MYSQL() {
        LibraryDAO getLibraryDAO() {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            return MySQLLibraryDAO.getInstance(sessionFactory);
        }

    },
    WEB_SERVICE_SOAP() {
        LibraryDAO getLibraryDAO() throws DBExceptions {
            try {
                return SoapServiceLibraryDAO.getInstance();
            } catch (MalformedURLException e) {
                throw new DBExceptions("SoapServiceLibraryDAO exception was thrown:", e);
            }
        }
    };

    abstract LibraryDAO getLibraryDAO() throws DBExceptions;
}
