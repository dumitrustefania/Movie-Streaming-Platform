package platform.fileio;

import platform.database.Credentials;
import platform.database.Movie;

public final class ActionInput {
    private String type;
    private String feature;
    private String page;
    private String startsWith;
    private String movie;
    private String count;
    private Credentials credentials;
    private int rate;
    private FilterInput filters;
    private String subscribedGenre;
    private Movie addedMovie;
    private String deletedMovie;

    public int getRate() {
        return rate;
    }

    public String getCount() {
        return count;
    }

    public String getMovie() {
        return movie;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public FilterInput getFilters() {
        return filters;
    }

    public String getPage() {
        return page;
    }

    public String getType() {
        return type;
    }

    public String getFeature() {
        return feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public Movie getAddedMovie() {
        return addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
