package platform.actions;

import platform.Database;
import platform.Movie;
import platform.User;
import platform.fileio.ActionInput;

public final class LikeAction extends Action {
    public LikeAction(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        User user = Database.getInstance().getCurrentUser();
        if (Database.getInstance().getCurrentMovie() != null
                && user.getWatchedMovies().contains(Database.getInstance().getCurrentMovie())) {
            Movie movie = Database.getInstance().getCurrentMovie();
            movie.setNumLikes(movie.getNumLikes() + 1);
            user.getLikedMovies().add(movie);
            Database.getInstance().addOutput();
        } else {
            Database.getInstance().addErrorOutput();
        }
    }
}
