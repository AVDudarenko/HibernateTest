package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class MainTest {

    private SessionFactory sessionFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @AfterEach
    protected void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    void saveNewObjectPet() {

        PetsInfo barsik = new PetsInfo("barsik", 12, "white", "barmaley");

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(barsik);
            session.getTransaction().commit();
        }

    }

    @Test
    public void testBasicUsage() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(new PetsInfo("barsik", 12, "white", "barmaley"));
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "select p from PetsInfo p";
        List<PetsInfo> result = session.createQuery(sql, PetsInfo.class).list();
        for (PetsInfo petsInfo : result) {
            System.out.println("Pet (" + petsInfo.getName() + ") : " + petsInfo.getAge());
        }
        session.getTransaction().commit();
        session.close();
    }

}