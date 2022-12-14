package POO_TV.actions;

import POO_TV.Database;
import POO_TV.Movie;
import POO_TV.User;
import POO_TV.fileio.ActionInput;
import POO_TV.pages.AuthenticatedHomepage;
import POO_TV.pages.UnauthenticatedHomepage;

import java.util.ArrayList;

public class PurchaseAction extends Action{
    public PurchaseAction(ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        System.out.println("Executing purchase action");

        if(Database.getInstance().getCurrentMovie() != null) {
            Movie movie = Database.getInstance().getCurrentMovie();
            User user = Database.getInstance().getCurrentUser();
            if(user.getCredentials().getAccountType().equals("premium")
                    && user.getNumFreePremiumMovies() > 0) {
                user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
            }
            else
                user.setTokensCount(user.getTokensCount() - 2);
            user.getPurchasedMovies().add(movie);
            Database.getInstance().addOutput();
        }
        else Database.getInstance().addErrorOutput();

    }
}
