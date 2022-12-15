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
     *
     */
    public abstract void execute();
}
