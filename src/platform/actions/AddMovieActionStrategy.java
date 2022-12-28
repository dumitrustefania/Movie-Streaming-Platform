package platform.actions;

import platform.database.Database;
import platform.database.Movie;
import platform.fileio.ActionInput;
import platform.observer.ObservableGenre;

public class AddMovieActionStrategy extends ActionStrategy {
    public AddMovieActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        for (Movie movie : Database.getInstance().getMovies()) {
            if (movie.getName().equals(actionInput.getAddedMovie().getName())) {
                Database.getInstance().addErrorOutput();
                return;
            }
        }

        Database.getInstance().getMovies().add(actionInput.getAddedMovie());
        ObservableGenre.getInstance().notifyObservers(actionInput.getAddedMovie());
    }
}
