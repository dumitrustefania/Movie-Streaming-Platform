package platform.pages;

import platform.database.Database;

import java.util.ArrayList;
import java.util.List;

public final class SeeDetailsPage extends Page {
    public SeeDetailsPage() {
        Database.getInstance().getHistory().add(this);
        this.allowedActions = new ArrayList<String>(List.of("purchase", "watch", "like", "rate", "subscribe"));
        this.allowedNextPages = new ArrayList<String>(List.of("homepage_auth", "movies",
                "upgrades", "logout", "see details"));
    }
}
