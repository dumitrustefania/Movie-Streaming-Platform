package platform.actions;

import platform.database.Database;
import platform.database.User;
import platform.fileio.ActionInput;

public final class BuyTokensActionStrategy extends ActionStrategy {
    public BuyTokensActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        User user = Database.getInstance().getCurrentUser();
        int count = Integer.parseInt(actionInput.getCount());

        user.setTokensCount(user.getTokensCount() + count);
        user.getCredentials().setBalance(String.valueOf(
                Integer.parseInt(user.getCredentials().getBalance()) - count));
    }
}
