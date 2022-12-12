package POO_TV.actions;

import POO_TV.Database;
import POO_TV.User;
import POO_TV.fileio.ActionInput;
import POO_TV.pages.AuthenticatedHomepage;

public class RegisterAction extends Action{
    public RegisterAction(ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        System.out.println("Executing register action.");
        User newUser = new User(actionInput.getCredentials());
        Database.getInstance().getUsers().add(newUser);
        Database.getInstance().setCurrentUser(newUser);
        Database.getInstance().setCurrentPage(new AuthenticatedHomepage());
        Database.getInstance().addOutput();
    }
}
