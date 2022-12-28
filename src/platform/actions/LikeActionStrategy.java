package platform.actions;

import platform.database.Database;
import platform.database.Movie;
import platform.database.User;
import platform.fileio.ActionInput;

public final class LikeActionStrategy extends ActionStrategy {
    public LikeActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        User user = Database.getInstance().getCurrentUser();

        Movie movie = Database.getInstance().getCurrentMovie();
        if (movie != null
                && user.getWatchedMovies().contains(movie)
                && !user.getLikedMovies().contains(movie)) {
            movie.setNumLikes(movie.getNumLikes() + 1);
            user.getLikedMovies().add(movie);
            Database.getInstance().addOutput();
        } else {
            Database.getInstance().addErrorOutput();
        }
    }
}
