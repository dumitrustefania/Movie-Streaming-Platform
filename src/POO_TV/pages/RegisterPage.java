package POO_TV.pages;

import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends Page{
    public RegisterPage() {
        this.pageName = "register";
        this.allowedActions = new ArrayList<String>(List.of("register"));
        this.allowedNextPages = new ArrayList<String>(List.of("register"));
    }
}
