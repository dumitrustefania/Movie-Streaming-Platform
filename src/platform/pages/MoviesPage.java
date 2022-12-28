package platform.pages;

import platform.database.Database;

import java.util.ArrayList;
import java.util.List;

public final class MoviesPage extends Page {
    public MoviesPage() {
        Database.getInstance().getHistory().add(this);
        this.name = "movies";
        this.allowedActions = new ArrayList<String>(List.of("search", "filter"));
        this.allowedNextPages = new ArrayList<String>(List.of("authenticated homepage",
                "see details", "logout", "movies"));
    }
}
