package POO_TV.actions;

import POO_TV.Database;
import POO_TV.fileio.ActionInput;
import POO_TV.pages.*;

public class ChangePageAction extends Action{
    public ChangePageAction(ActionInput actionInput) {
        super(actionInput);
    }
    @Override
    public void execute() {
        System.out.println("Changing the page");
        switch (actionInput.getPage()) {
            case "login":
                Database.getInstance().setCurrentPage(new LoginPage());
                break;
            case "register":
                Database.getInstance().setCurrentPage(new RegisterPage());
                break;
            case "logout":
                Database.getInstance().setCurrentUser(null);
                Database.getInstance().setCurrentPage(new UnauthenticatedHomepage());
                Database.getInstance().getCurrentUserMovies().clear();
                break;
            case "movies":
                Database.getInstance().setCurrentPage(new MoviesPage());
                Database.getInstance().addOutput();
                break;
            case "see details":
                Database.getInstance().setCurrentPage(new SeeDetailsPage());
                Database.getInstance().addOutput();
                break;
        }
    }
}
