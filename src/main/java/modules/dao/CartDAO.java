package modules.dao;

import modules.enteties.Cart;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CartDAO extends BaseDAO<Cart, Long> {
    public CartDAO() {
        super(Cart.class);
    }

    public List<Cart> findByUserId(Long userId) {
        Session session = getSession();
        List<Cart> cartItems = session.createQuery("FROM Cart WHERE userId = :userId", Cart.class)
                .setParameter("userId", userId)
                .list();
        session.close();
        return cartItems;
    }

    public void deleteByUserId(Long userId) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Cart WHERE userId = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
        transaction.commit();
        session.close();
    }
}
