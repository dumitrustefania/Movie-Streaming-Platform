package platform.pages;

import platform.database.Database;

import java.util.ArrayList;
import java.util.List;

public final class LoginPage extends Page {
    public LoginPage() {
        this.name = "login";
        this.allowedActions = new ArrayList<String>(List.of("login"));
        this.allowedNextPages = new ArrayList<String>(List.of("login"));
    }
}
