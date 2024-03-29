package platform.actions;

import platform.database.Database;
import platform.fileio.ActionInput;

public final class BackActionStrategy extends ActionStrategy {
    @Override
    public void execute() {
        if (Database.getInstance().getHistory().size() <= 1) {
            Database.getInstance().addErrorOutput();
            return;
        }

        Database.getInstance().getHistory().pop();
        String previousPage = Database.getInstance().getHistory().pop().getName();
        ActionInput actionInput = new ActionInput();
        actionInput.setPage(previousPage);
        new ChangePageActionStrategy(actionInput).execute();
    }
}
