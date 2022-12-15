package platform.actions;

import platform.Database;
import platform.Movie;
import platform.fileio.ActionInput;
import platform.pages.*;

import java.util.ArrayList;
import java.util.List;

public final class ChangePageAction extends Action {
    public ChangePageAction(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
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
                Database.getInstance().setCurrentMovie(null);
                Database.getInstance().getCurrentUser().getAllowedMovies();
                Database.getInstance().addOutput();
            }
            case "see details" -> {
                for (Movie movie : Database.getInstance().getCurrentUserMovies()) {
                    if (movie.getName().equals(actionInput.getMovie())) {
                        Database.getInstance().setCurrentUserMovies(
                                new ArrayList<Movie>(List.of(movie)));
                        Database.getInstance().setCurrentMovie(movie);
                        Database.getInstance().addOutput();
                        Database.getInstance().setCurrentPage(new SeeDetailsPage());
                        return;
                    }
                }
                Database.getInstance().addErrorOutput();
            }
            case "upgrades" -> Database.getInstance().setCurrentPage(new UpgradesPage());
            default -> throw new IllegalStateException("Unexpected value");
        }
    }
}
