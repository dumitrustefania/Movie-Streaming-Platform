package platform.actions;

import platform.Credentials;
import platform.Database;
import platform.User;
import platform.fileio.ActionInput;
import platform.pages.AuthenticatedHomepage;
import platform.pages.UnauthenticatedHomepage;

import java.util.ArrayList;

public final class LoginAction extends Action {
    public LoginAction(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        ArrayList<User> users = Database.getInstance().getUsers();

        for (User user : users) {
            Credentials credentials = user.getCredentials();
            if (credentials.getName().equals(actionInput.getCredentials().getName())
                    && credentials.getPassword().equals(
                            actionInput.getCredentials().getPassword())) {
                Database.getInstance().setCurrentUser(user);
                Database.getInstance().setCurrentPage(new AuthenticatedHomepage());
                Database.getInstance().addOutput();
                return;
            }
        }


        Database.getInstance().addErrorOutput();
        Database.getInstance().setCurrentPage(new UnauthenticatedHomepage());
    }
}
