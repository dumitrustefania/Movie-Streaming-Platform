package POO_TV.pages;

import POO_TV.actions.Action;

import java.util.ArrayList;

public abstract class Page {
    String pageName;
    ArrayList<String> allowedActions;
    ArrayList<String> allowedNextPages;

    public ArrayList<String> getAllowedActions() {
        return allowedActions;
    }

    public void setAllowedActions(ArrayList<String> allowedActions) {
        this.allowedActions = allowedActions;
    }

    public ArrayList<String> getAllowedNextPages() {
        return allowedNextPages;
    }

    public void setAllowedNextPages(ArrayList<String> allowedNextPages) {
        this.allowedNextPages = allowedNextPages;
    }

    public String getPageName() {
        return pageName;
    }


    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void execute(Action action) {
        action.execute();
    }
}
