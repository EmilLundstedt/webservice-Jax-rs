
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package dao;

import java.util.List;

import entity.Movie;

/**

 @author _
 */
public interface MovieManagerInterface {

    public void deleteMovie(Movie m);

    public void deleteMovieById(int id);

   // public void editMovie(Movie m);

    public List<Movie> orderByName();

    public List<Movie> orderByRating();

    public void postMovie(Movie m);

    public List<Movie> showAll(int limit);

    public List<Movie> showAllWithName(String name, int limit);

    public List<Movie> showAllWithRating(int rating, int limit);

    public Movie showID(int id);

    public Movie showMovie(Movie m);

    public void updateMovie(Movie m, int id);

    public List<Movie> getAll();
}


//~ Formatted by Jindent --- http://www.jindent.com
