package platform.actions;

import platform.database.Database;
import platform.fileio.ActionInput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class SearchActionStrategy extends ActionStrategy {
    public SearchActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        /* Set the array of currently displayed movies to
           all the movies whose names start with the given
           prefix string. */

        Database.getInstance().setCurrentUserMovies(Database.getInstance().getCurrentUserMovies()
                .stream()
                .filter(m -> m.getName().startsWith(actionInput.getStartsWith()))
                .collect(Collectors.toCollection(ArrayList::new)));
        Database.getInstance().addOutput();
    }
}
