package platform.pages;

import platform.actions.Action;

import java.util.ArrayList;

public abstract class Page {
    protected ArrayList<String> allowedActions;
    protected ArrayList<String> allowedNextPages;

    /**
     * @param action
     */
    public void execute(final Action action) {
        action.execute();
    }

    public final ArrayList<String> getAllowedActions() {
        return allowedActions;
    }

    public final ArrayList<String> getAllowedNextPages() {
        return allowedNextPages;
    }
}
