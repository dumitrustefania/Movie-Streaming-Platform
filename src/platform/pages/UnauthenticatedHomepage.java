package platform.pages;

import java.util.ArrayList;
import java.util.List;

public final class UnauthenticatedHomepage extends Page {
    public UnauthenticatedHomepage() {
        this.name = "unauthenticated homepage";
        this.allowedActions = new ArrayList<String>(List.of());
        this.allowedNextPages = new ArrayList<String>(List.of("login", "register"));
    }
}
