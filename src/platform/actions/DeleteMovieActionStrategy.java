package platform.actions;

import platform.database.Database;
import platform.database.Movie;
import platform.database.User;
import platform.fileio.ActionInput;

public class DeleteMovieActionStrategy extends ActionStrategy {
    public DeleteMovieActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        String deletedMovieName = actionInput.getDeletedMovie();
        Movie deletedMovie = null;
        for (Movie movie : Database.getInstance().getMovies()) {
            if (movie.getName().equals(deletedMovieName)) {
                deletedMovie = movie;
                break;
            }
        }

        if (deletedMovie == null) {
            Database.getInstance().addErrorOutput();
            return;
        }

        for (User user : Database.getInstance().getUsers()) {
            if (user.getPurchasedMovies().contains(deletedMovie)) {
                if (user.getCredentials().getAccountType().equals("premium")) {
                    user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
                } else {
                    user.setTokensCount(user.getTokensCount() + 2);
                }
                user.getPurchasedMovies().remove(deletedMovie);
            }
            user.getWatchedMovies().remove(deletedMovie);
            user.getLikedMovies().remove(deletedMovie);
            user.getRatedMovies().remove(deletedMovie);
            Database.getInstance().getCurrentUserMovies().remove(deletedMovie);
        }
        Database.getInstance().getMovies().remove(deletedMovie);
    }
}
