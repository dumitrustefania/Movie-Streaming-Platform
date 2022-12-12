package POO_TV;

import POO_TV.fileio.Output;
import POO_TV.pages.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class Database {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;


    public ArrayList<Movie> getCurrentUserMovies() {
        return currentUserMovies;
    }

    public void setCurrentUserMovies(ArrayList<Movie> currentUserMovies) {
        this.currentUserMovies = currentUserMovies;
    }

    private ArrayList<Movie> currentUserMovies;

    private User currentUser;

    private Page currentPage;
    public void addErrorOutput() {
        currentUser = null;
        currentUserMovies.clear();

        ArrayNode outputData = Result.getInstance().getResult();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node = objectMapper.valueToTree(new Output("Error", currentUserMovies, currentUser));
        outputData.add(node);
    }

    public void addOutput() {
        ArrayNode outputData = Result.getInstance().getResult();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(new Output(null, currentUserMovies, currentUser));
        outputData.add(node);
    }
    public Page getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    private final static Database instance = new Database();

    private Database() {}

    public static Database getInstance() {
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
