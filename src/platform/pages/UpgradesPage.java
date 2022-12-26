package platform.pages;

import platform.database.Database;

import java.util.ArrayList;
import java.util.List;

public final class UpgradesPage extends Page {
    public UpgradesPage() {
        Database.getInstance().getHistory().add(this);
        this.allowedActions = new ArrayList<String>(List.of("buy premium account", "buy tokens"));
        this.allowedNextPages = new ArrayList<String>(List.of("homepage_auth", "movies",
                "upgrades", "logout"));
    }
}
