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
        /* Depending on the input, change the page to the required one and
           execute other necessary actions. */
        switch (actionInput.getPage()) {
            // Create new login page.
            case "login" -> Database.getInstance().setCurrentPage(new LoginPage());
            // Create new register page.
            case "register" -> Database.getInstance().setCurrentPage(new RegisterPage());
            /* Create new unauthenticated homepage, set current user to NULL and
               clear the list of current movies on screen. */
            case "logout" -> {
                Database.getInstance().setCurrentPage(new UnauthenticatedHomepage());
                Database.getInstance().setCurrentUser(null);
                Database.getInstance().getCurrentUserMovies().clear();
            }
            /* Create new movies page, set current movie to NULL,
               compute all allowed movies for current user and add output. */
            case "movies" -> {
                Database.getInstance().setCurrentPage(new MoviesPage());
                Database.getInstance().setCurrentMovie(null);
                Database.getInstance().getAllowedMovies();
                Database.getInstance().addOutput();
            }
            /* Check whether the wanted movie exists in the list of movies currently
               appearing on screen. If yes, set it as the current movie and create a new
               see details page. If not, remain on the same page and add error output.*/
            case "see details" -> {
                System.out.println(Database.getInstance().getCurrentUser().getCredentials().getName() + actionInput.getMovie());
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
            // Create new upgrades page.
            case "upgrades" -> Database.getInstance().setCurrentPage(new UpgradesPage());
            case "authenticated homepage" -> Database.getInstance().setCurrentPage(new AuthenticatedHomepage());
            default -> throw new IllegalStateException("Unexpected value");
        }
    }
}
