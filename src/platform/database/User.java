package platform.database;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public final class User {
    private static final int NUM_INITIAL_FREE_MOVIES = 15;
    private Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies = NUM_INITIAL_FREE_MOVIES;
    private ArrayList<Movie> purchasedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> watchedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> likedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> ratedMovies = new ArrayList<Movie>();
    private ArrayList<Notification> notifications = new ArrayList<>();
    @JsonIgnore
    private HashMap<Movie, Integer> rateForMovie = new HashMap<>();

    public User(final Credentials credentials) {
        this.credentials = credentials;
    }

    public User() {
    }

    /**
     * If the new movie contains one of the genres subscribed
     * by the user, he gets notified.
     * @param movieName - the new movie added to the database
     */
    public void updateObserver(final String movieName) {
        for (Notification notification : notifications) {
            if (notification.getMovieName().equals(movieName)) {
                return;
            }
        }

        notifications.add(new Notification(movieName, "ADD"));
    }

    /**
     * Create a movie recommendation for the user.
     * Steps:
     * - making a top of the genres appreciated by the user,
     *     by sorting them in descending order according to
     *     the number of likes;
     * - sorting movies from the database in descending order
     *     according to the total number of likes;
     * - finding the first movie in the database with the highest
     *     number of likes that has not been seen before by
     *     the user and which is part of the movie genre most
     *     appreciated by him;
     */
    public void createRecommendation() {
        HashMap<String, Integer> likedGenres = new HashMap<>();
        for (Movie movie : likedMovies) {
            for (String genre : movie.getGenres()) {
                if (!likedGenres.containsKey(genre)) {
                    likedGenres.put(genre, 1);
                } else {
                    likedGenres.put(genre, likedGenres.get(genre) + 1);
                }
            }
        }

        ArrayList<Map.Entry<String, Integer>> likedGenresList =
                new ArrayList<>(likedGenres.entrySet());

        likedGenresList.sort((o1, o2) -> {
            if (Objects.equals(o1.getValue(), o2.getValue())) {
                String key1 = o1.getKey();
                String key2 = o2.getKey();
                return key1.compareTo(key2);
            }

            return Integer.compare(o1.getValue(), o2.getValue());
        });

        Database.getInstance().getAllowedMovies();
        Database.getInstance().getCurrentUserMovies()
                .sort(((o1, o2) -> Integer.compare(o2.getNumLikes(), o1.getNumLikes())));

        AtomicBoolean foundRecommendation = new AtomicBoolean(false);
        likedGenresList.forEach(entry -> {
            if (!foundRecommendation.get()) {
                for (Movie movie : Database.getInstance().getCurrentUserMovies()) {
                    if (!watchedMovies.contains(movie) && !foundRecommendation.get()) {
                        for (String genre : movie.getGenres()) {
                            if (genre.equals(entry.getKey())) {
                                notifications.add(new Notification(movie.getName(),
                                        "Recommendation"));
                                foundRecommendation.set(true);
                            }
                        }
                    }
                }
            }
        });

        if (!foundRecommendation.get()) {
            notifications.add(new Notification("No recommendation", "Recommendation"));
        }
        Database.getInstance().setCurrentUserMovies(null);
        Database.getInstance().addOutput();
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public HashMap<Movie, Integer> getRateForMovie() {
        return rateForMovie;
    }

    public void setRateForMovie(final HashMap<Movie, Integer> rateForMovie) {
        this.rateForMovie = rateForMovie;
    }
}
