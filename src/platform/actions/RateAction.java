package platform.actions;

import platform.Database;
import platform.Movie;
import platform.User;
import platform.fileio.ActionInput;

public final class RateAction extends Action {
    public static final int MAX_RATE = 5;
    public RateAction(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        User user = Database.getInstance().getCurrentUser();
        if (actionInput.getRate() <= MAX_RATE
                && Database.getInstance().getCurrentMovie() != null
                && user.getWatchedMovies().contains(Database.getInstance().getCurrentMovie())) {
            Movie movie = Database.getInstance().getCurrentMovie();
            movie.setNumRatings(movie.getNumRatings() + 1);
            movie.setSumRatings(movie.getSumRatings() + (double) actionInput.getRate());
            movie.setRating(movie.getSumRatings() / movie.getNumRatings());
            Database.getInstance().getCurrentUser().getRatedMovies().add(movie);
            Database.getInstance().addOutput();
        } else {
            Database.getInstance().addErrorOutput();
        }

    }
}
