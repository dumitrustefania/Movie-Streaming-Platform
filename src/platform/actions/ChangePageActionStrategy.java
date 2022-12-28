package platform.actions;

import platform.database.Database;
import platform.database.Movie;
import platform.fileio.ActionInput;
import platform.pages.*;

import java.util.ArrayList;
import java.util.List;

public final class ChangePageActionStrategy extends ActionStrategy {
    public ChangePageActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        switch (actionInput.getPage()) {
            case "login" -> Database.getInstance().setCurrentPage(new LoginPage());
            case "register" -> Database.getInstance().setCurrentPage(new RegisterPage());
            case "logout" -> {
                Database.getInstance().setCurrentPage(new UnauthenticatedHomepage());
                Database.getInstance().setCurrentUser(null);
                Database.getInstance().getCurrentUserMovies().clear();
            }
            case "movies" -> {
                Database.getInstance().setCurrentPage(new MoviesPage());
                Database.getInstance().setCurrentMovie(null);
                Database.getInstance().getAllowedMovies();
                Database.getInstance().addOutput();
            }
            case "see details" -> {
                for (Movie movie : Database.getInstance().getCurrentUserMovies()) {
                    if (movie.getName().equals(actionInput.getMovie())) {
                        Database.getInstance().setCurrentUserMovies(
                                new ArrayList<>(List.of(movie)));
                        Database.getInstance().setCurrentMovie(movie);
                        Database.getInstance().addOutput();
                        Database.getInstance().setCurrentPage(new SeeDetailsPage());
                        return;
                    }
                }
                Database.getInstance().addErrorOutput();
            }
            case "upgrades" -> Database.getInstance().setCurrentPage(new UpgradesPage());
            case "authenticated homepage" ->
                    Database.getInstance().setCurrentPage(new AuthenticatedHomepage());
            default -> throw new IllegalStateException("Unexpected value");
        }
    }
}
