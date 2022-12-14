package POO_TV.actions;

import POO_TV.Database;
import POO_TV.Movie;
import POO_TV.User;
import POO_TV.fileio.ActionInput;

public class RateAction extends Action{
    public RateAction(ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        System.out.println("Executing rate action");
        User user = Database.getInstance().getCurrentUser();
        if(actionInput.getRate() <= 5
                && Database.getInstance().getCurrentMovie() != null
                && user.getWatchedMovies().contains(Database.getInstance().getCurrentMovie())) {
            Movie movie = Database.getInstance().getCurrentMovie();
            movie.setNumRatings(movie.getNumRatings()+1);
            movie.setSumRatings(movie.getSumRatings() + (double) actionInput.getRate());
            movie.setRating(movie.getSumRatings() / movie.getNumRatings());
            Database.getInstance().getCurrentUser().getRatedMovies().add(movie);
            Database.getInstance().addOutput();
        }
        else Database.getInstance().addErrorOutput();

    }
}
