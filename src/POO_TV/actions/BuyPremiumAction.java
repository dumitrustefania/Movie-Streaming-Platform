package POO_TV.actions;

import POO_TV.Database;
import POO_TV.User;
import POO_TV.fileio.ActionInput;

public class BuyPremiumAction extends Action{
    public BuyPremiumAction(ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        System.out.println("Executing buy premium acount action.");
        User user =  Database.getInstance().getCurrentUser();
        user.setTokensCount(user.getTokensCount() - 10);
        user.getCredentials().setAccountType("premium");
    }
}
