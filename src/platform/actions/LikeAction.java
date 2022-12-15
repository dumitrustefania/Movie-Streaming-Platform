package platform.actions;

import platform.database.Database;
import platform.database.Movie;
import platform.database.User;
import platform.fileio.ActionInput;

public final class LikeAction extends Action {
    public LikeAction(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        User user = Database.getInstance().getCurrentUser();

        Movie movie = Database.getInstance().getCurrentMovie();
        // Check whether the movie has been watched.
        if (movie != null
                && user.getWatchedMovies().contains(movie)) {
            // Update number of likes.
            movie.setNumLikes(movie.getNumLikes() + 1);
            // Add movie to liked movies set of the user.
            user.getLikedMovies().add(movie);
            Database.getInstance().addOutput();
        } else {
            Database.getInstance().addErrorOutput();
        }
    }
}
