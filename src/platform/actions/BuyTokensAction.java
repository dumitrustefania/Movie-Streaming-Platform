package platform.actions;

import platform.database.Database;
import platform.database.User;
import platform.fileio.ActionInput;

public final class BuyTokensAction extends Action {
    public BuyTokensAction(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        User user = Database.getInstance().getCurrentUser();
        int count = Integer.parseInt(actionInput.getCount());

        // Increase tokens count by given value.
        user.setTokensCount(user.getTokensCount() + count);
        // Decrease balance count by given value.
        user.getCredentials().setBalance(String.valueOf(
                Integer.parseInt(user.getCredentials().getBalance()) - count));
    }
}
