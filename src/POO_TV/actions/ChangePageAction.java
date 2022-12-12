package POO_TV.actions;

import POO_TV.Database;
import POO_TV.Movie;
import POO_TV.fileio.ActionInput;
import POO_TV.pages.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ChangePageAction extends Action{
    public ChangePageAction(ActionInput actionInput) {
        super(actionInput);
    }
    @Override
    public void execute() {
        System.out.println("Changing the page");
        switch (actionInput.getPage()) {
            case "login" -> Database.getInstance().setCurrentPage(new LoginPage());
            case "register" -> Database.getInstance().setCurrentPage(new RegisterPage());
            case "logout" -> {
                Database.getInstance().setCurrentUser(null);
                Database.getInstance().setCurrentPage(new UnauthenticatedHomepage());
                Database.getInstance().getCurrentUserMovies().clear();
            }
            case "movies" -> {
                Database.getInstance().setCurrentPage(new MoviesPage());
                Database.getInstance().setCurrentUserMovies(new ArrayList<Movie>(
                        Database.getInstance().getMovies()
                                .stream()
                                .filter(m -> !m.getCountriesBanned().contains(
                                        Database.getInstance().getCurrentUser().getCredentials().getCountry()))
                                .collect(Collectors.toCollection(ArrayList::new))));
                Database.getInstance().addOutput();
            }
            case "see details" -> {
                Database.getInstance().setCurrentPage(new SeeDetailsPage());
                Database.getInstance().addOutput();
            }
        }
    }
}
