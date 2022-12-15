package platform.fileio;

import platform.Credentials;

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
}
