package modules.dao;

import modules.enteties.Game;
import org.hibernate.Session;
import java.util.List;

public class GameDAO extends BaseDAO<Game, Long> {
    public GameDAO() {
        super(Game.class);
    }

    public List<Game> findGamesByGenre(Long genreId) {
        Session session = getSession();
        List<Game> games = session.createQuery("FROM Game WHERE genre.id = :genreId", Game.class)
                .setParameter("genreId", genreId)
                .list();
        session.close();
        return games;
    }

    public Game findByTitle(String title) {
        Session session = getSession();
        Game game = session.createQuery("FROM Game WHERE title = :title", Game.class)
                .setParameter("title", title)
                .uniqueResult();
        session.close();
        return game;
    }
}
