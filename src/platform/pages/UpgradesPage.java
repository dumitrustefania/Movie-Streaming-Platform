package platform.pages;

import platform.database.Database;

import java.util.ArrayList;
import java.util.List;

public final class UpgradesPage extends Page {
    public UpgradesPage() {
        Database.getInstance().getHistory().add(this);
        this.name = "upgrades";
        this.allowedActions = new ArrayList<String>(List.of("buy premium account", "buy tokens"));
        this.allowedNextPages = new ArrayList<String>(List.of("authenticated homepage", "movies",
                "upgrades", "logout"));
    }
}
