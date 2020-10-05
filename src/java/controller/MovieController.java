package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

import dao.MovieManagerInterface;

import entity.Movie;

@Named(value = "movieController")
@RequestScoped
public class MovieController {

    private int id;
    private String name;
    private int rating;
    private String description;
    private List<Movie> allMovies;
    private int counter;
    @Inject
    MovieManagerInterface MI;

    public MovieController() {
    }

    private boolean checkIfOkay() {
        if ((rating > 5) || (rating < 1)) {
            rating = 0;

            return false;
        }

        if (name.isEmpty() || description.isEmpty()) {
            
            return false;
        }

        return true;
    }

    public void copy(Movie m) {
        name = m.getName();
        rating = m.getRating();
        description = m.getDescription();
        id = m.getId();
    }

    public void deleteMovie(Movie m) {
        MI.deleteMovie(m);
        showMovies();
    }

    public void find(Movie m) {
        List<Movie> MovieList = new ArrayList();
        Movie movie = MI.showMovie(m);

        MovieList.add(movie);
        allMovies = MovieList;
        counter = MI.getAll().size();
    }

    public void post() {
        Movie m = new Movie(name, rating, description);

        if (checkIfOkay()) {
            MI.postMovie(m);

            showMovies();
        } else {

            // run error message
        }
    }

    @PostConstruct
    public void showMovies() {
        this.allMovies = MI.showAll(100);
        this.counter = MI.getAll().size();
    }

    public void showMoviesByRating() {
        allMovies = MI.orderByRating();
    }

    public void showMoviesWithName(String name) {
        allMovies = MI.showAllWithName(name, 100);
    }

    public void update() {
        if (checkIfOkay()) {
            Movie m = new Movie(name, rating, description);

            MI.updateMovie(m, id);
            showMovies();
        }
    }

    public List<Movie> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(List<Movie> allMovies) {
        this.allMovies = allMovies;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
