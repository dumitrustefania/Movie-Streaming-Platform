package platform.actions;

import platform.Database;
import platform.User;
import platform.fileio.ActionInput;
import platform.pages.AuthenticatedHomepage;

public final class RegisterAction extends Action {
    public RegisterAction(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        User newUser = new User(actionInput.getCredentials());
        Database.getInstance().getUsers().add(newUser);
        Database.getInstance().setCurrentUser(newUser);
        Database.getInstance().setCurrentPage(new AuthenticatedHomepage());
        Database.getInstance().addOutput();
    }
}