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

        // Decrease tokens count by 10.
        user.setTokensCount(user.getTokensCount() - PREMIUM_ACCOUNT_PRICE);
        // Set account to premium.
        user.getCredentials().setAccountType("premium");
    }
}
