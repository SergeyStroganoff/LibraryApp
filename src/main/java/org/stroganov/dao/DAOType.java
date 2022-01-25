package org.stroganov.dao;

import org.hibernate.SessionFactory;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.util.HibernateUtil;
import org.stroganov.wsClient.LibraryWebServiceClient;

public enum DAOType {

    JSON() {
        public LibraryDAO getLibraryDAO() throws DBExceptions {
            return JsonLibraryDAO.getInstance();
        }
    },
    MYSQL() {
        public LibraryDAO getLibraryDAO() {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            return MySQLLibraryDAO.getInstance(sessionFactory);
        }

    },
    WEB_SERVICE_SOAP() {
        public LibraryDAO getLibraryDAO() throws DBExceptions {
            return LibraryWebServiceClient.getInstance();
        }
    };

    public abstract LibraryDAO getLibraryDAO() throws DBExceptions;
}
