package platform.pages;

import platform.database.Database;

import java.util.ArrayList;
import java.util.List;

public final class LoginPage extends Page {
    public LoginPage() {
        Database.getInstance().getHistory().add(this);
        this.allowedActions = new ArrayList<String>(List.of("login"));
        this.allowedNextPages = new ArrayList<String>(List.of("login"));
    }
}
