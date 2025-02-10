package modules.dao;

import modules.enteties.Genre;
import org.hibernate.Session;
import java.util.List;

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
