package platform.actions;

import platform.database.Database;
import platform.fileio.ActionInput;
import platform.observable.ObservableGenre;

public final class SubscribeActionStrategy extends ActionStrategy {

    public SubscribeActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        String genre = actionInput.getSubscribedGenre();

        if (!Database.getInstance().getCurrentPage().getAllowedActions().contains("subscribe")
                || !Database.getInstance().getCurrentMovie().getGenres().contains(genre)) {
            Database.getInstance().addErrorOutput();
            return;
        }

        ObservableGenre.getInstance().addObserver(genre, Database.getInstance().getCurrentUser());
    }
}
