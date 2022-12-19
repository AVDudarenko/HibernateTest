package org.example;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class PetsDAO {
    public void save(PetsInfo petsInfo) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession(); //открываем сессию
        session.beginTransaction();
        session.save(petsInfo);
        session.flush();
        session.close();
    }

    public void delete(PetsInfo petsInfo) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(petsInfo);
        session.flush();
        session.close();
    }

    public List<PetsInfo> getAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PetsInfo> criteriaQuery = builder.createQuery(PetsInfo.class);
        return session.createQuery(criteriaQuery).getResultList();
    }

    public PetsInfo getById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.get(PetsInfo.class, id);
    }

}
