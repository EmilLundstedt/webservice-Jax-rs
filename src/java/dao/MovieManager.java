
package dao;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Movie;

@Stateless
public class MovieManager implements MovieManagerInterface {

    @PersistenceContext
    EntityManager em;

    @Override
    public void deleteMovie(Movie m) {
        if (!em.contains(m)) {
            m = em.merge(movieToLowerCase(m));
        }

        em.remove(movieToLowerCase(m));
    }

    @Override
    public void deleteMovieById(int id) {
        em.remove(em.find(Movie.class, id));
    }

  //  @Override
  //  public void editMovie(Movie m) {
  //     em.merge(m);
  //  }

    private Movie movieToLowerCase(Movie m) {
        m.setDescription(m.getDescription().toLowerCase());
        m.setName(m.getName().toLowerCase());

        return m;
    }

    @Override
    public List<Movie> orderByName() {
        String queryLine = "SELECT m FROM Movie m ORDER BY m.name ASC";
        Query query = em.createQuery(queryLine).setMaxResults(100);

        return query.getResultList();
    }

    @Override
    public List<Movie> orderByRating() {
        String queryLine = "SELECT m FROM Movie m ORDER BY m.rating DESC";
        Query query = em.createQuery(queryLine).setMaxResults(100);

        return query.getResultList();
    }

    @Override
    public void postMovie(Movie m) {
        em.persist(movieToLowerCase(m));
    }

    @Override
    public List<Movie> showAll(int limit) {
        if ((limit > 100) || (limit == 0)) {
            limit = 100;
        }

        Query query = em.createQuery("SELECT m FROM Movie m ORDER BY m.id DESC").setMaxResults(limit);

        return query.getResultList();
    }

    @Override
    public List<Movie> showAllWithName(String name, int limit) {
        if ((limit > 100) || (limit == 0)) {
            limit = 100;
        }

        String queryLine = "SELECT m FROM Movie m WHERE m.name LIKE '" + name.toLowerCase() + "%' ORDER BY m.id DESC";
        Query query = em.createQuery(queryLine).setMaxResults(limit);

        return query.getResultList();
    }

    @Override
    public List<Movie> showAllWithRating(int rating, int limit) {
        if ((limit > 100) || (limit == 0)) {
            limit = 100;
        }

        Query query = em.createQuery("SELECT m FROM Movie m Where m.rating=" + rating + " ORDER BY m.id DESC")
                .setMaxResults(limit);

        return query.getResultList();
    }

    @Override
    public Movie showID(int id) {
        return em.find(Movie.class, id);
    }

    @Override
    public Movie showMovie(Movie m) {
        return m;
    }

    @Override
    public void updateMovie(Movie m, int id) {
        Movie main = em.find(Movie.class, id);

        main.setDescription(m.getDescription());
        main.setName(m.getName());
        main.setRating(m.getRating());
        em.merge(movieToLowerCase(main));
    }

    @Override
    public List<Movie> getAll() {
        Query query = em.createQuery("SELECT m FROM Movie m");

        return query.getResultList();
    }
}
