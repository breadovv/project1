package modules.dao;

import modules.enteties.Games;
import org.hibernate.Session;
import java.util.List;

public class GameDAO extends BaseDAO<Games, Long> {
    public GameDAO() {
        super(Games.class);
    }

    public List<Games> findGamesByGenre(Long genreId) {
        try (Session session = getSession()) {
            return session.createQuery("FROM Games WHERE genre.id = :genreId", Games.class)
                    .setParameter("genreId", genreId)
                    .list();
        }
    }

    public Games findByTitle(String title) {
        try (Session session = getSession()) {
            return session.createQuery("FROM Games WHERE title = :title", Games.class)
                    .setParameter("title", title)
                    .uniqueResult();
        }
    }

    public List<Games> searchGamesByTitle(String keyword) {
        try (Session session = getSession()) {
            return session.createQuery("FROM Games g WHERE LOWER(g.title) LIKE LOWER(:keyword)", Games.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .list();
        }
    }


}

