package platform.pages;

import platform.database.Database;

import java.util.ArrayList;
import java.util.List;

public final class UnauthenticatedHomepage extends Page {
    public UnauthenticatedHomepage() {
        Database.getInstance().getHistory().add(this);
        this.allowedActions = new ArrayList<String>(List.of());
        this.allowedNextPages = new ArrayList<String>(List.of("login", "register"));
    }
}
