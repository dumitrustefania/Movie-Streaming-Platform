package platform.pages;

import java.util.ArrayList;
import java.util.List;

public final class MoviesPage extends Page {
    public MoviesPage() {
        this.allowedActions = new ArrayList<String>(List.of("search", "filter"));
        this.allowedNextPages = new ArrayList<String>(List.of("homepage_auth", "see details", "logout", "movies"));
    }
}
