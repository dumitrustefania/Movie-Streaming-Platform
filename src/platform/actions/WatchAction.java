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
        if (Database.getInstance().getCurrentMovie() != null
                && user.getPurchasedMovies().contains(Database.getInstance().getCurrentMovie())) {
            Movie movie = Database.getInstance().getCurrentMovie();
            user.getWatchedMovies().add(movie);
            Database.getInstance().addOutput();
        } else {
            Database.getInstance().addErrorOutput();
        }
    }
}
