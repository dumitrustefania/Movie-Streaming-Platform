package platform.actions;

import platform.Database;
import platform.Movie;
import platform.User;
import platform.fileio.ActionInput;

public final class PurchaseAction extends Action {
    public PurchaseAction(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        Movie movie = Database.getInstance().getCurrentMovie();

        if (movie != null) {
            User user = Database.getInstance().getCurrentUser();
            /* Try to purchase movie for free if user is premium and
               still has free movies left to buy. If that is not possible
               or user si standard, pay 2 tokens for a movie. */
            if (user.getCredentials().getAccountType().equals("premium")
                    && user.getNumFreePremiumMovies() > 0) {
                user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
            } else {
                user.setTokensCount(user.getTokensCount() - 2);
            }

            // Add movie to purchased movies set of the user.
            user.getPurchasedMovies().add(movie);
            Database.getInstance().addOutput();
        } else {
            Database.getInstance().addErrorOutput();
        }
    }
}
