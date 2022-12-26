package platform.actions;

import platform.database.Database;

public class BackActionStrategy extends ActionStrategy{
    @Override
    public void execute() {
        Database.getInstance().setCurrentPage(Database.getInstance().getHistory().pop());
    }
}
