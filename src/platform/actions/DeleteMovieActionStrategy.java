package platform.actions;

import platform.database.Database;
import platform.fileio.ActionInput;

public class DeleteMovieActionStrategy extends ActionStrategy{
    public DeleteMovieActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        Database.getInstance().getMovies().remove(actionInput.getDeletedMovie());
    }
}
