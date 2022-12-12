package POO_TV.actions;

import POO_TV.fileio.ActionInput;

public abstract class Action {
    protected ActionInput actionInput;

    public Action(ActionInput actionInput) {
        this.actionInput = actionInput;
    }

    public Action() {
    }

    public abstract void execute();
}
