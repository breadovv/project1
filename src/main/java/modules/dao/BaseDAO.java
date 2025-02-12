package modules.dao;

import modules.enteties.Basic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class BaseDAO<T extends Basic<ID>, ID extends Serializable> {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private final Class<T> entityClass;

    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public void create(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public T read(ID id) {
        Session session = getSession();
        T entity = session.get(entityClass, id);
        session.close();
        return entity;
    }

    public List<T> readAll() {
        Session session = getSession();
        List<T> entities = session.createQuery("FROM " + entityClass.getSimpleName(), entityClass).list();
        session.close();
        return entities;
    }

    public void update(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
}