package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 * Class for setup Session Factory
 */
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "There was an error building the factory");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
