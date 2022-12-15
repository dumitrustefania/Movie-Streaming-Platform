package platform.pages;

import java.util.ArrayList;
import java.util.List;

public final class AuthenticatedHomepage extends Page {
    public AuthenticatedHomepage() {
        this.allowedActions = new ArrayList<String>(List.of());
        this.allowedNextPages = new ArrayList<String>(List.of("movies", "upgrades", "logout"));
    }
}
