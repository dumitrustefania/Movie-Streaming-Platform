package platform.fileio;

import platform.Movie;
import platform.User;

import java.util.ArrayList;

public final class Input {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private ArrayList<ActionInput> actions;

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<ActionInput> getActions() {
        return actions;
    }
}
