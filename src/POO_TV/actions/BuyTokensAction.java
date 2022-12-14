package POO_TV.actions;

import POO_TV.Database;
import POO_TV.User;
import POO_TV.fileio.ActionInput;
import POO_TV.pages.AuthenticatedHomepage;

public class BuyTokensAction extends Action{
    public BuyTokensAction(ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        System.out.println("Executing buy tokens action.");
        User user =  Database.getInstance().getCurrentUser();
        int count = Integer.parseInt(actionInput.getCount());
        user.setTokensCount(user.getTokensCount()+count);
        user.getCredentials().setBalance(String.valueOf(Integer.parseInt(user.getCredentials().getBalance()) - count));
    }
}
