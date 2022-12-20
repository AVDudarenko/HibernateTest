package org.example;

import org.hibernate.Session;

import java.util.List;

/**
 * Class which implement CRUD operations for DB.
 */
public class PetsDAO {
    public void save(PetsInfo petsInfo) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession(); //открываем сессию
        session.beginTransaction();
        session.persist(petsInfo);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(PetsInfo petsInfo) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(petsInfo);
        session.close();
    }

    public List<PetsInfo> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select p from PetsInfo p";
        List<PetsInfo> result = session.createQuery(sql, PetsInfo.class).list();
        for (PetsInfo petsInfo : result) {
            System.out.println("Pet (" + petsInfo.getName() + ") : " + petsInfo.getAge());
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public PetsInfo getById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.get(PetsInfo.class, id);
    }

}
