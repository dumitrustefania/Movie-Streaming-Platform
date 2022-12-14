package POO_TV;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class Movie {
    String name;
    int year, duration;
    ArrayList<String> genres, actors, countriesBanned;
    int numLikes = 0;
    int numRatings = 0;
    @JsonIgnore
    double sumRatings = 0;

    public double getSumRatings() {
        return sumRatings;
    }

    public void setSumRatings(double sumRatings) {
        this.sumRatings = sumRatings;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    double rating = 0;
    public Movie() {
    }

    public Movie(String name, int year, int duration, ArrayList<String> genres, ArrayList<String> actors, ArrayList<String> countriesBanned) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", genres=" + genres +
                ", actors=" + actors +
                ", countriesBanned=" + countriesBanned +
                '}';
    }
}
