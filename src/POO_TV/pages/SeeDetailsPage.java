package POO_TV.pages;

import java.util.ArrayList;
import java.util.List;

public class SeeDetailsPage extends Page{
    public SeeDetailsPage() {
        this.pageName = "see details";
        this.allowedActions = new ArrayList<String>(List.of("purchase", "watch", "like", "rate"));
        this.allowedNextPages = new ArrayList<String>(List.of("homepage_auth", "movies", "upgrades", "logout", "see details"));
    }
}
