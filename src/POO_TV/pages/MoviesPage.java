package POO_TV.pages;

import java.util.ArrayList;
import java.util.List;

public class MoviesPage extends Page{
    public MoviesPage() {
        this.pageName = "login";
        this.allowedActions = new ArrayList<String>(List.of("search", "filter"));
        this.allowedNextPages = new ArrayList<String>(List.of("homepage_auth", "see details", "logout"));
    }
}
