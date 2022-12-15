package platform.actions;

import platform.database.Credentials;
import platform.database.Database;
import platform.database.User;
import platform.fileio.ActionInput;
import platform.pages.AuthenticatedHomepage;
import platform.pages.UnauthenticatedHomepage;

import java.util.ArrayList;

public final class LoginActionStrategy extends ActionStrategy {
    public LoginActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        ArrayList<User> users = Database.getInstance().getUsers();

        // Check whether user with given credentials exists in the database.
        for (User user : users) {
            Credentials credentials = user.getCredentials();
            if (credentials.getName().equals(actionInput.getCredentials().getName())
                    && credentials.getPassword().equals(
                            actionInput.getCredentials().getPassword())) {
                // Update current user and page (authenticated homapage).
                Database.getInstance().setCurrentUser(user);
                Database.getInstance().setCurrentPage(new AuthenticatedHomepage());
                Database.getInstance().addOutput();
                return;
            }
        }

        /* In case user does not exist, add error output
           and update page (unauthenticated homapage). */
        Database.getInstance().addErrorOutput();
        Database.getInstance().setCurrentPage(new UnauthenticatedHomepage());
    }
}
