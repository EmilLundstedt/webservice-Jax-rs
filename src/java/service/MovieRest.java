
package service;

import java.util.List;

import javax.ejb.Stateless;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.MovieManagerInterface;

import entity.Movie;

@Stateless
@Path("movies")
public class MovieRest {

    
    @Inject
    MovieManagerInterface sdao;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createBurger(Movie m) {
        sdao.postMovie(m);
    }

    @DELETE
    @Path("id={id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteById(@PathParam("id") Integer id) {
        sdao.deleteMovieById(id);
    }

    @PUT
    @Path("id={id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void editMovie(@PathParam("id") Integer id, Movie m) {
        sdao.updateMovie(m, id);
    }

    @GET
    @Path("orderbyname")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> orderByName() {
        return sdao.orderByName();
    }

    @GET
    @Path("orderbyrating")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> orderByRating() {
        return sdao.orderByRating();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> getAll() {
        return sdao.showAll(100);
    }

    @GET
    @Path("limit={limit}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> getAll(@PathParam("limit") Integer limit) {
        return sdao.showAll(limit);
    }

    @GET
    @Path("id={id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Movie getID(@PathParam("id") Integer id) {
        return sdao.showID(id);
    }

    @GET
    @Path("name={name}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> getListWithName(@PathParam("name") String name) {
        return sdao.showAllWithName(name, 5);
    }

    @GET
    @Path("name={name}&limit={limit}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> getListWithName(@PathParam("name") String name, @PathParam("limit") int limit) {
        return sdao.showAllWithName(name, limit);
    }

    @GET
    @Path("rating={rating}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> getRating(@PathParam("rating") Integer rating) {
        return sdao.showAllWithRating(rating, 5);
    }

    @GET
    @Path("rating={rating}&limit={limit}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> getRating(@PathParam("rating") Integer rating, @PathParam("limit") int limit) {
        return sdao.showAllWithRating(rating, limit);
    }
}

