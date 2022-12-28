package platform.observer;

import platform.database.Database;
import platform.database.Movie;
import platform.database.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Observer pattern implementation - users subscribe to
 * a movie genre and get notified whenever a new movie
 * containing one of their subscribed genres is added
 * to the database.
 */
public final class ObservableGenre {
    private static ObservableGenre INSTANCE = null;
    private final HashMap<String, ArrayList<User>> observers = new HashMap<>();

    private ObservableGenre() {
    }

    /**
     * Lazy singleton implementation of the observable.
     */
    public static ObservableGenre getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ObservableGenre();
        }

        return INSTANCE;
    }

    /**
     * Add user to the list of observers for the specified
     * genre.
     * @param genre - the genre that the user subscribes to
     * @param user - that user that subscribes
     */
    public void addObserver(final String genre, final User user) {
        if (!observers.containsKey(genre)) {
            observers.put(genre, new ArrayList<>());
        }

        if (observers.get(genre).contains(user)) {
            Database.getInstance().addErrorOutput();
            return;
        }

        observers.get(genre).add(user);
    }

    /**
     * Notify all genre-specific observers about the new movie.
     * @param movie - the new movie added to the database
     */
    public void notifyObservers(final Movie movie) {
        for (String genre : movie.getGenres()) {
            if (observers.containsKey(genre)) {
                for (User user : observers.get(genre)) {
                    if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                        user.updateObserver(movie.getName());
                    }
                }
            }
        }
    }
}
