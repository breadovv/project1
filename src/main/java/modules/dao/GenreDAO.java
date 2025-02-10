package modules.dao;

import modules.entities.Genre;
import org.hibernate.Session;

public class GenreDAO extends BaseDAO<Genre, Long> {
    public GenreDAO() {
        super(Genre.class);
    }

    public Genre findByName(String name) {
        Session session = getSession();
        Genre genre = session.createQuery("FROM Genre WHERE name = :name", Genre.class)
                .setParameter("name", name)
                .uniqueResult();
        session.close();
        return genre;
    }
}
