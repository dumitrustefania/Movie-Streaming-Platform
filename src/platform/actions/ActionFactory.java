package platform.actions;

import platform.fileio.ActionInput;

public class ActionFactory {
    /**
     * Helps to create the factory design pattern, instantiating
     * the needed Action subclass depending on the input.
     * @param actionInput the current action given in input
     * @return an Action subclass
     */
    public static Action createAction(final ActionInput actionInput) {
        switch (actionInput.getType()) {
            case "on page" : {
                switch (actionInput.getFeature()) {
                    case "login" -> {
                        return new LoginAction(actionInput);
                    }
                    case "register" -> {
                        return new RegisterAction(actionInput);
                    }
                    case "search" -> {
                        return new SearchAction(actionInput);
                    }
                    case "filter" -> {
                        return new FilterAction(actionInput);
                    }
                    case "purchase" -> {
                        return new PurchaseAction(actionInput);
                    }
                    case "watch" -> {
                        return new WatchAction(actionInput);
                    }
                    case "like" -> {
                        return new LikeAction(actionInput);
                    }
                    case "rate" -> {
                        return new RateAction(actionInput);
                    }
                    case "buy premium account" -> {
                        return new BuyPremiumAction(actionInput);
                    }
                    case "buy tokens" -> {
                        return new BuyTokensAction(actionInput);
                    }
                    default -> throw new IllegalStateException("Unexpected value");
                }
            }
            case "change page" :
                return new ChangePageAction(actionInput);
            default:
                throw new IllegalStateException("Unexpected value");
        }
    }
}
