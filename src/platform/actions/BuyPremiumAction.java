package platform.actions;

import platform.Database;
import platform.User;
import platform.fileio.ActionInput;

public final class BuyPremiumAction extends Action {
    public static final int PREMIUM_ACCOUNT_PRICE = 10;

    public BuyPremiumAction(final ActionInput actionInput) {
        super(actionInput);
    }
    @Override
    public void execute() {
        User user = Database.getInstance().getCurrentUser();
        user.setTokensCount(user.getTokensCount() - PREMIUM_ACCOUNT_PRICE);
        user.getCredentials().setAccountType("premium");
    }
}
