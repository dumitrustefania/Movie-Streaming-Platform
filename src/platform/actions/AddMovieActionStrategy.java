package platform.actions;

import platform.database.Database;
import platform.fileio.ActionInput;

public class AddMovieActionStrategy extends ActionStrategy{
    public AddMovieActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        Database.getInstance().getMovies().add(actionInput.getAddedMovie());
    }
}
