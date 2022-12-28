package platform.actions;

import platform.database.Database;
import platform.database.Movie;
import platform.database.User;
import platform.fileio.ActionInput;

public final class WatchActionStrategy extends ActionStrategy {
    public WatchActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        User user = Database.getInstance().getCurrentUser();
        Movie movie = Database.getInstance().getCurrentMovie();
        if (movie != null
                && user.getPurchasedMovies().contains(movie)) {
            // Add movie to watched movies set of the user.
            if (!user.getWatchedMovies().contains(movie)) {
                user.getWatchedMovies().add(movie);
                Database.getInstance().addOutput();
            }
        } else {
            Database.getInstance().addErrorOutput();
        }
    }
}
