package POO_TV.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginPage extends Page{
    public LoginPage() {
        this.pageName = "login";
        this.allowedActions = new ArrayList<String>(List.of("login"));
        this.allowedNextPages = new ArrayList<String>(List.of("login"));
    }
}
