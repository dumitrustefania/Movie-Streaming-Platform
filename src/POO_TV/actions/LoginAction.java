package POO_TV.actions;

import POO_TV.Database;
import POO_TV.User;
import POO_TV.fileio.ActionInput;
import POO_TV.pages.AuthenticatedHomepage;
import POO_TV.pages.UnauthenticatedHomepage;

import java.util.ArrayList;

public class LoginAction extends Action{
    public LoginAction(ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        System.out.println("Executing login action");
        ArrayList<User> users = Database.getInstance().getUsers();

        for(User user: users)
            if(user.getCredentials().getName().equals(actionInput.getCredentials().getName()) &&
               user.getCredentials().getPassword().equals(actionInput.getCredentials().getPassword()))
            {
                Database.getInstance().setCurrentUser(user);
                Database.getInstance().setCurrentPage(new AuthenticatedHomepage());
                Database.getInstance().addOutput();
                System.out.println("Login cu succes");
                return;
            }
        System.out.println("Login fail");
        Database.getInstance().addErrorOutput();
        Database.getInstance().setCurrentPage(new UnauthenticatedHomepage());
    }
}
