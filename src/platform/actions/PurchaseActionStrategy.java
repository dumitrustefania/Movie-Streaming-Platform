package platform.actions;

import platform.database.Database;
import platform.database.Movie;
import platform.database.User;
import platform.fileio.ActionInput;

public final class PurchaseActionStrategy extends ActionStrategy {
    public PurchaseActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        Movie movie = Database.getInstance().getCurrentMovie();
        User user = Database.getInstance().getCurrentUser();

        if (movie != null && !user.getPurchasedMovies().contains(movie)) {
            if (user.getCredentials().getAccountType().equals("premium")
                    && user.getNumFreePremiumMovies() > 0) {
                user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
            } else {
                user.setTokensCount(user.getTokensCount() - 2);
            }

            user.getPurchasedMovies().add(movie);
            Database.getInstance().addOutput();
        } else {
            Database.getInstance().addErrorOutput();
        }
    }
}
