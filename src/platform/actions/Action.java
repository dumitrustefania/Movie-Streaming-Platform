package platform.actions;

import platform.fileio.ActionInput;

public abstract class Action {
    protected ActionInput actionInput;

    public Action(final ActionInput actionInput) {
        this.actionInput = actionInput;
    }

    public Action() {
    }

    /**
     *  This Action abstract class declares the execute() method
     *  that will be used by the context (represented by the current page)
     *  to call the algorithm defined by the concrete strategies.
     */
    public abstract void execute();
}
