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
        if (Database.getInstance().getCurrentMovie() != null) {
            Movie movie = Database.getInstance().getCurrentMovie();
            User user = Database.getInstance().getCurrentUser();
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
