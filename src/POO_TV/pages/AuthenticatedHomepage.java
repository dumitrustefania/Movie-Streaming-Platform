package POO_TV.pages;

import java.util.ArrayList;
import java.util.List;

public class AuthenticatedHomepage extends Page{
    public AuthenticatedHomepage() {
        this.pageName = "homepage_auth";
        this.allowedActions = new ArrayList<String>(List.of());
        this.allowedNextPages = new ArrayList<String>(List.of("movies", "upgrades", "logout"));
    }
}
