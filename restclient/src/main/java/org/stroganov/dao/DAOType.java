package org.stroganov.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.restservice.LibraryRestServiceClient;
import org.stroganov.util.HibernateUtil;

@Component
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
    WEB_SERVICE_REST() {
        @Override
        public LibraryDAO getLibraryDAO() throws DBExceptions {
            return LibraryRestServiceClient.getInstance();
        }
    };

    public abstract LibraryDAO getLibraryDAO() throws DBExceptions;
}
