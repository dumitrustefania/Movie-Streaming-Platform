package platform.actions;

import platform.fileio.ActionInput;

public class ActionStrategyFactory {
    /**
     * Helps to create the factory design pattern, instantiating
     * the needed Action subclass depending on the input.
     * @param actionInput the current action given in input
     * @return an Action subclass
     */
    public static ActionStrategy createAction(final ActionInput actionInput) {
        switch (actionInput.getType()) {
            case "on page" : {
                switch (actionInput.getFeature()) {
                    case "login" -> {
                        return new LoginActionStrategy(actionInput);
                    }
                    case "register" -> {
                        return new RegisterActionStrategy(actionInput);
                    }
                    case "search" -> {
                        return new SearchActionStrategy(actionInput);
                    }
                    case "filter" -> {
                        return new FilterActionStrategy(actionInput);
                    }
                    case "purchase" -> {
                        return new PurchaseActionStrategy(actionInput);
                    }
                    case "watch" -> {
                        return new WatchActionStrategy(actionInput);
                    }
                    case "like" -> {
                        return new LikeActionStrategy(actionInput);
                    }
                    case "rate" -> {
                        return new RateActionStrategy(actionInput);
                    }
                    case "buy premium account" -> {
                        return new BuyPremiumActionStrategy(actionInput);
                    }
                    case "buy tokens" -> {
                        return new BuyTokensActionStrategy(actionInput);
                    }
                    default -> throw new IllegalStateException("Unexpected value");
                }
            }
            case "change page" :
                return new ChangePageActionStrategy(actionInput);
            default:
                throw new IllegalStateException("Unexpected value");
        }
    }
}
