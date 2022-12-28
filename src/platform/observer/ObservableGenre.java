package platform.observer;

import platform.database.Database;
import platform.database.Movie;
import platform.database.User;

import java.util.ArrayList;
import java.util.HashMap;

public final class ObservableGenre {
    private ObservableGenre() {
    }

    private static ObservableGenre INSTANCE = null;

    /**
     * Singleton implementation of the database.
     */
    public static ObservableGenre getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ObservableGenre();
        return INSTANCE;
    }

    private final HashMap<String, ArrayList<User>> observers = new HashMap<>();

    public void addObserver(String genre, User user) {
        if (!observers.containsKey(genre))
            observers.put(genre, new ArrayList<>());
        if (observers.get(genre).contains(user)) {
            Database.getInstance().addErrorOutput();
            return;
        }
        observers.get(genre).add(user);
    }

    public void notifyObservers(Movie movie) {
        for (String genre : movie.getGenres()) {
            System.out.println(genre);
            if (observers.containsKey(genre)) {
                for (User user : observers.get(genre)) {
                    if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry()))
                        user.updateObserver(movie.getName());
                }
            }
        }
    }
}
