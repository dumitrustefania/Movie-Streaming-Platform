package platform.pages;

import platform.actions.ActionStrategy;

import java.util.ArrayList;

public abstract class Page {
    protected String name;
    /**
     * Strings array containing all actions that can be executed
     * while being on a subclass page.
     */
    protected ArrayList<String> allowedActions;
    /**
     * Strings array containing all the next pages that can
     * be visited from the subclass page.
     */
    protected ArrayList<String> allowedNextPages;

    /**
     * A page is the context for applying the Action strategy, using
     * the execute() method.
     *
     * @param actionStrategy an Action subclass that executes the desired strategy
     */
    public void execute(final ActionStrategy actionStrategy) {
        actionStrategy.execute();
    }

    public final ArrayList<String> getAllowedActions() {
        return allowedActions;
    }

    public final ArrayList<String> getAllowedNextPages() {
        return allowedNextPages;
    }

    public final String getName() {
        return name;
    }
}
