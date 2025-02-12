package modules.dao;

import modules.enteties.Genres;
import org.hibernate.Session;

public class GenreDAO extends BaseDAO<Genres, Long> {
    public GenreDAO() {
        super(Genres.class);
    }

    public Genres findByName(String name) {
        Session session = getSession();
        Genres genres = session.createQuery("FROM Genres WHERE name = :name", Genres.class)
                .setParameter("name", name)
                .uniqueResult();
        session.close();
        return genres;
    }
}
