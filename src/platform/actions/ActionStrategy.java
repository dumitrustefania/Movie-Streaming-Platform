package platform.actions;

import platform.fileio.ActionInput;

public abstract class ActionStrategy {
    protected ActionInput actionInput;

    public ActionStrategy(final ActionInput actionInput) {
        this.actionInput = actionInput;
    }

    public ActionStrategy() {
    }

    /**
     *  This Action abstract class declares the execute() method
     *  that will be used by the context (represented by the current page)
     *  to call the algorithm defined by the concrete strategies.
     */
    public abstract void execute();
}
