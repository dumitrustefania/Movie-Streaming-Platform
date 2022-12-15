package platform.actions;

import platform.Database;
import platform.User;
import platform.fileio.ActionInput;

public final class BuyTokensAction extends Action {
    public BuyTokensAction(final ActionInput actionInput) {
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
