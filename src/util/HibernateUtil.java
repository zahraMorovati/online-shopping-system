package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure("config/hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }
}
