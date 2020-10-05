
package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int rating;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Movie() {
    }

    public Movie(String name, int rating, String description) {
        this.name = name;
        this.rating = rating;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
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
