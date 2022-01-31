package org.stroganov.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private HibernateUtil() {
    }

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            if (LOGGER != null) {
                LOGGER.error("build SessionFactory failed :" + ex);
            }
            throw ex;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        // Close all cached and active connection pools
        getSessionFactory().close();
    }

}

