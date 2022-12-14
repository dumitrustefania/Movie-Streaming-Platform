package POO_TV.actions;

import POO_TV.Database;
import POO_TV.Movie;
import POO_TV.User;
import POO_TV.fileio.ActionInput;

public class WatchAction extends Action{
    public WatchAction(ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        System.out.println("Executing watch action");
        User user = Database.getInstance().getCurrentUser();
        if(Database.getInstance().getCurrentUserMovies().size() > 0
           && user.getPurchasedMovies().contains(Database.getInstance().getCurrentUserMovies().get(0))) {
            Movie movie = Database.getInstance().getCurrentUserMovies().get(0);
            user.getWatchedMovies().add(movie);
            Database.getInstance().addOutput();
        }
        else Database.getInstance().addErrorOutput();
    }
}
