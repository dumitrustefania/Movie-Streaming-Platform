package platform.pages;

import java.util.ArrayList;
import java.util.List;

public final class RegisterPage extends Page {
    public RegisterPage() {
        this.allowedActions = new ArrayList<String>(List.of("register"));
        this.allowedNextPages = new ArrayList<String>(List.of("register"));
    }
}
