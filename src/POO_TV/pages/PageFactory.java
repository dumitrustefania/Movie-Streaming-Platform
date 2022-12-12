package POO_TV.pages;

public class PageFactory {
    public enum PageType {
        Login, Register
    }

    public static Page createPage(PageType pageType) {
        return switch (pageType) {
            case Login -> new LoginPage();
            case Register -> new RegisterPage();
        };
    }
}
