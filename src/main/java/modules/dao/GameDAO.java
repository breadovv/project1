package modules.dao;

import modules.enteties.Game;
import org.hibernate.Session;
import java.util.List;

public class GameDAO extends BaseDAO<Game, Long> {
    public GameDAO() {
        super(Game.class);
    }

    public List<Game> findGamesByGenre(Long genreId) {
        try (Session session = getSession()) {
            return session.createQuery("FROM Game WHERE genre.id = :genreId", Game.class)
                    .setParameter("genreId", genreId)
                    .list();
        }
    }

    public Game findByTitle(String title) {
        try (Session session = getSession()) {
            return session.createQuery("FROM Game WHERE title = :title", Game.class)
                    .setParameter("title", title)
                    .uniqueResult();
        }
    }

    public List<Game> searchGamesByTitle(String keyword) {
        try (Session session = getSession()) {
            return session.createQuery("FROM Game g WHERE LOWER(g.title) LIKE LOWER(:keyword)", Game.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .list();
        }
    }


}

