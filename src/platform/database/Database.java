package platform.database;

import platform.fileio.Output;
import platform.pages.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class Database {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private Movie currentMovie;
    private ArrayList<Movie> currentUserMovies;
    private User currentUser;
    private Page currentPage;

    private Database() {
    }

    private final static Database INSTANCE = new Database();

    /**
     * Singleton implementation of the database.
     */
    public static Database getInstance() {
        return INSTANCE;
    }

    /**
     * Create a new error output, transform it to JSON and
     * append it to the output.
     */
    public void addErrorOutput() {
        ArrayNode outputData = Result.getInstance().getResult();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node = objectMapper.valueToTree(new Output("Error", new ArrayList<Movie>(), null));
        outputData.add(node);
    }

    /**
     *  Create a new non-error output, transform it to JSON and
     *  append it to the output.
     */
    public void addOutput() {
        ArrayNode outputData = Result.getInstance().getResult();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(new Output(null, currentUserMovies, currentUser));
        outputData.add(node);
    }

    /**
     * From the movie list, filter those allowed in the current user's country.
     */
    public void getAllowedMovies() {
        currentUserMovies = movies
                .stream()
                .filter(m -> !m.getCountriesBanned().contains(
                        currentUser.getCredentials().getCountry()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Movie getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentMovie(final Movie currentMovie) {
        this.currentMovie = currentMovie;
    }

    public ArrayList<Movie> getCurrentUserMovies() {
        return currentUserMovies;
    }

    public void setCurrentUserMovies(final ArrayList<Movie> currentUserMovies) {
        this.currentUserMovies = currentUserMovies;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }
}
