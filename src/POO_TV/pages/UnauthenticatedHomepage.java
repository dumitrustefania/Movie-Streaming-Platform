package POO_TV.pages;

import java.util.ArrayList;
import java.util.List;

public class UnauthenticatedHomepage extends Page{
    public UnauthenticatedHomepage() {
        this.pageName = "homepage_unauth";
        this.allowedActions = new ArrayList<String>(List.of());
        this.allowedNextPages = new ArrayList<String>(List.of("login", "register"));
    }
}
