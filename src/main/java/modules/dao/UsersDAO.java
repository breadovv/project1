package modules.dao;

import modules.entities.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UsersDAO extends BaseDAO<Users, Long> {
    public UsersDAO() {
        super(Users.class);
    }

    public Users findByname(String name) {
        Session session = getSession();
        Users user = session.createQuery("FROM Users WHERE name = :name", Users.class)
                .setParameter("name", name)
                .uniqueResult();
        session.close();
        return user;
    }

    public Users findByPhone(String phone) {
        Session session = getSession();
        Users user = session.createQuery("FROM Users WHERE phone = :phone", Users.class)
                .setParameter("phone", phone)
                .uniqueResult();
        session.close();
        return user;
    }

    public Users findByEmail(String email) {
        Session session = getSession();
        Users user = session.createQuery("FROM Users WHERE email = :email", Users.class)
                .setParameter("email", email)
                .uniqueResult();
        session.close();
        return user;
    }

    public List<Users> findAllUsers() {
        Session session = getSession();
        List<Users> users = session.createQuery("FROM Users", Users.class).list();
        session.close();
        return users;
    }

    public void deleteByName(String name) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Users user = findByname(name);
        if (user != null) {
            session.delete(user);
        }
        transaction.commit();
        session.close();
    }
}