package platform.fileio;

import platform.Movie;
import platform.User;

import java.util.ArrayList;

public final class Output {
    private String error;
    private ArrayList<Movie> currentMoviesList;
    private User currentUser;

    public Output(final String error, final ArrayList<Movie> currentMoviesList,
                  final User currentUser) {
        this.error = error;
        this.currentMoviesList = currentMoviesList;
        this.currentUser = currentUser;
    }

    public Output() {
    }

    public String getError() {
        return error;
    }

    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
