package POO_TV;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class User {
    Credentials credentials;

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    int tokensCount;
    int numFreePremiumMovies = 15;
    ArrayList<Movie> purchasedMovies = new ArrayList<Movie>();
    ArrayList<Movie> watchedMovies = new ArrayList<Movie>();
    ArrayList<Movie> likedMovies = new ArrayList<Movie>();
    ArrayList<Movie> ratedMovies = new ArrayList<Movie>();

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public User() {
    }

    public User(Credentials credentials) {
        this.credentials = credentials;
    }

    public void getAllowedMovies() {
        Database.getInstance().setCurrentUserMovies(new ArrayList<Movie>(
                Database.getInstance().getMovies()
                        .stream()
                        .filter(m -> !m.getCountriesBanned().contains(
                                Database.getInstance().getCurrentUser().getCredentials().getCountry()))
                        .collect(Collectors.toCollection(ArrayList::new))));
    }
}
