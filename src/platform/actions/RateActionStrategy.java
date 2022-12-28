package platform.actions;

import platform.database.Database;
import platform.database.Movie;
import platform.database.User;
import platform.fileio.ActionInput;

public final class RateActionStrategy extends ActionStrategy {
    public static final int MAX_RATE = 5;
    public RateActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        User user = Database.getInstance().getCurrentUser();

        Movie movie = Database.getInstance().getCurrentMovie();
        // Check whether the movie has been watched and rate <= 5.
        if (actionInput.getRate() <= MAX_RATE
                && movie != null
                && user.getWatchedMovies().contains(movie)) {
            // Change the movie's rating.
            if(user.getRateForMovie().containsKey(movie)) {
                movie.setSumRatings(movie.getSumRatings() - (double) user.getRateForMovie().get(movie));
            } else {
                movie.setNumRatings(movie.getNumRatings() + 1);
            }

            user.getRateForMovie().put(movie, actionInput.getRate());
            movie.setSumRatings(movie.getSumRatings() + (double) actionInput.getRate());
            movie.setRating(movie.getSumRatings() / movie.getNumRatings());

            // Add movie to rated movies set of the user.
            if(!user.getRatedMovies().contains(movie))
                user.getRatedMovies().add(movie);

            Database.getInstance().addOutput();
        } else {
            Database.getInstance().addErrorOutput();
        }

    }
}
