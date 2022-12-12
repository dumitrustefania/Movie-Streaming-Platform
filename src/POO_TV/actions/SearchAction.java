package POO_TV.actions;

import POO_TV.Database;
import POO_TV.User;
import POO_TV.fileio.ActionInput;
import POO_TV.pages.AuthenticatedHomepage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SearchAction extends Action{

    public SearchAction(ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        System.out.println("Executing search action.");
        Database.getInstance().setCurrentUserMovies(Database.getInstance().getCurrentUserMovies()
                .stream()
                .filter(m -> m.getName().startsWith(actionInput.getStartsWith()))
                .collect(Collectors.toCollection(ArrayList::new)));
        Database.getInstance().addOutput();
    }
}
