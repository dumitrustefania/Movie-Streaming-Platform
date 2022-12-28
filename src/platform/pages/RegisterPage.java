package platform.pages;

import platform.database.Database;

import java.util.ArrayList;
import java.util.List;

public final class RegisterPage extends Page {
    public RegisterPage() {
        this.name = "register";
        this.allowedActions = new ArrayList<String>(List.of("register"));
        this.allowedNextPages = new ArrayList<String>(List.of("register"));
    }
}
