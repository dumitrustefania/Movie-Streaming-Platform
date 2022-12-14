package POO_TV.pages;

import java.util.ArrayList;
import java.util.List;

public class UpgradesPage extends Page{
    public UpgradesPage() {
        this.pageName = "see details";
        this.allowedActions = new ArrayList<String>(List.of("buy premium account", "buy tokens"));
        this.allowedNextPages = new ArrayList<String>(List.of("homepage_auth", "movies", "upgrades", "logout"));
    }
}
