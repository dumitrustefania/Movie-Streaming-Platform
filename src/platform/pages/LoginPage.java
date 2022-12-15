package platform.pages;

import java.util.ArrayList;
import java.util.List;

public final class LoginPage extends Page {
    public LoginPage() {
        this.allowedActions = new ArrayList<String>(List.of("login"));
        this.allowedNextPages = new ArrayList<String>(List.of("login"));
    }
}
