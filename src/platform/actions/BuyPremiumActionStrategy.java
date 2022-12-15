package platform.actions;

import platform.database.Database;
import platform.database.User;
import platform.fileio.ActionInput;

public final class BuyPremiumActionStrategy extends ActionStrategy {
    public static final int PREMIUM_ACCOUNT_PRICE = 10;

    public BuyPremiumActionStrategy(final ActionInput actionInput) {
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
