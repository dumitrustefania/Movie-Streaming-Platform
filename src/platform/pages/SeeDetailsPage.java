package platform.pages;

import java.util.ArrayList;
import java.util.List;

public final class SeeDetailsPage extends Page {
    public SeeDetailsPage() {
        this.allowedActions = new ArrayList<String>(List.of("purchase", "watch", "like", "rate"));
        this.allowedNextPages = new ArrayList<String>(List.of("homepage_auth", "movies",
                "upgrades", "logout", "see details"));
    }
}
