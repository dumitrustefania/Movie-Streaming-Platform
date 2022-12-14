package POO_TV.actions;

import POO_TV.Database;
import POO_TV.Movie;
import POO_TV.User;
import POO_TV.fileio.ActionInput;

public class LikeAction extends Action{
    public LikeAction(ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        System.out.println("Executing like action");
        User user = Database.getInstance().getCurrentUser();
        if(Database.getInstance().getCurrentUserMovies().size() > 0
                && user.getWatchedMovies().contains(Database.getInstance().getCurrentUserMovies().get(0))) {
            Movie movie = Database.getInstance().getCurrentUserMovies().get(0);
            movie.setNumLikes(movie.getNumLikes()+1);
            user.getLikedMovies().add(movie);
            Database.getInstance().addOutput();
        }
        else Database.getInstance().addErrorOutput();

    }
}
