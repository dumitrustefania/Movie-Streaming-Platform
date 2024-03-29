package platform.pages;

import platform.database.Database;

import java.util.ArrayList;
import java.util.List;

public final class AuthenticatedHomepage extends Page {
    public AuthenticatedHomepage() {
        Database.getInstance().getHistory().clear();
        Database.getInstance().getHistory().add(this);
        this.name = "authenticated homepage";
        this.allowedActions = new ArrayList<String>(List.of());
        this.allowedNextPages = new ArrayList<String>(List.of("movies", "upgrades",
                "logout", "authenticated homepage"));
    }
}
