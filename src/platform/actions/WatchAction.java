package platform.actions;

import platform.Database;
import platform.Movie;
import platform.User;
import platform.fileio.ActionInput;

public final class WatchAction extends Action {
    public WatchAction(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        User user = Database.getInstance().getCurrentUser();
        Movie movie = Database.getInstance().getCurrentMovie();
        if (movie != null
                && user.getPurchasedMovies().contains(movie)) {
            // Add movie to watched movies set of the user.
            user.getWatchedMovies().add(movie);
            Database.getInstance().addOutput();
        } else {
            Database.getInstance().addErrorOutput();
        }
    }
}
