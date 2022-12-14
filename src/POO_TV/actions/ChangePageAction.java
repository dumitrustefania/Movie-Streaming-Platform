package POO_TV.actions;

import POO_TV.Database;
import POO_TV.Movie;
import POO_TV.fileio.ActionInput;
import POO_TV.pages.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChangePageAction extends Action{
    public ChangePageAction(ActionInput actionInput) {
        super(actionInput);
    }
    @Override
    public void execute() {
        System.out.println("Changing the page to" + actionInput.getPage());
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
                Database.getInstance().getCurrentUser().getAllowedMovies();
                System.out.println("dupa ce am schimbat pe pagina movies vad ca am allowed x movies:" +Database.getInstance().getCurrentUserMovies().size());
                Database.getInstance().addOutput();
            }
            case "see details" -> {
                System.out.println("movie wanted:" + actionInput.getMovie());
                Database.getInstance().getCurrentUser().getAllowedMovies();
                Database.getInstance().setCurrentPage(new SeeDetailsPage());
                for(Movie movie : Database.getInstance().getCurrentUserMovies())
                    if (movie.getName().equals(actionInput.getMovie())) {
                        Database.getInstance().setCurrentUserMovies(new ArrayList<Movie>(List.of(movie)));
                        Database.getInstance().addOutput();
                        return;
                }
                Database.getInstance().getCurrentUserMovies().clear();
                Database.getInstance().addErrorOutput();
            }
            case "upgrades" -> Database.getInstance().setCurrentPage(new UpgradesPage());
        }
    }
}
