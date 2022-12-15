package platform.actions;

import platform.database.Database;
import platform.database.Movie;
import platform.fileio.ActionInput;
import platform.fileio.FilterInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class FilterActionStrategy extends ActionStrategy {
    public FilterActionStrategy(final ActionInput actionInput) {
        super(actionInput);
    }

    /**
     * Sort the movies list by rating value, either in increasing
     * or decreasing order, depending on the requirements.
     * @param filter required filters
     */
    public void sortByRating(final FilterInput filter) {
        Database.getInstance().setCurrentUserMovies(Database.getInstance().getCurrentUserMovies()
                .stream()
                .sorted(new Comparator<Movie>() {
                    @Override
                    public int compare(final Movie o1, final Movie o2) {
                        if (filter.getSort().getRating().equals("increasing")) {
                            return Double.compare(o1.getRating(), o2.getRating());
                        } else {
                            return Double.compare(o2.getRating(), o1.getRating());
                        }
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    /**
     * Sort the movies list by duration time value, either in increasing
     * or decreasing order, depending on the requirements.
     * @param filter required filters
     */
    public void sortByDuration(final FilterInput filter) {
        Database.getInstance().setCurrentUserMovies(Database.getInstance().getCurrentUserMovies()
                .stream()
                .sorted(new Comparator<Movie>() {
                    @Override
                    public int compare(final Movie o1, final Movie o2) {
                        if (filter.getSort().getDuration().equals("increasing")) {
                            return Integer.compare(o1.getDuration(), o2.getDuration());
                        } else {
                            return Integer.compare(o2.getDuration(), o1.getDuration());
                        }
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    /**
     * Filter the movies list, keeping those that have all
     * genres required.
     * @param genres list of required genres
     */
    public void filterByGenre(final ArrayList<String> genres) {
        Database.getInstance().setCurrentUserMovies(new ArrayList<Movie>(
                Database.getInstance().getCurrentUserMovies()
                        .stream()
                        .filter(m -> {
                            boolean containsAllGenres = true;
                            for (String genre : genres) {
                                if (!m.getGenres().contains(genre)) {
                                    containsAllGenres = false;
                                    break;
                                }
                            }

                            return containsAllGenres;
                        })
                        .collect(Collectors.toCollection(ArrayList::new))));
    }

    /**
     * Filter the movies list, keeping those that have all
     * actors required.
     * @param actors list of required actors
     */
    public void filterByActor(final ArrayList<String> actors) {
        Database.getInstance().setCurrentUserMovies(new ArrayList<Movie>(
                Database.getInstance().getCurrentUserMovies()
                        .stream()
                        .filter(m -> {
                            boolean containsAllActors = true;
                            for (String actor : actors) {
                                if (!m.getActors().contains(actor)) {
                                    containsAllActors = false;
                                    break;
                                }
                            }

                            return containsAllActors;
                        })
                        .collect(Collectors.toCollection(ArrayList::new))));
    }
    @Override
    public void execute() {
        FilterInput filters = actionInput.getFilters();
        Database.getInstance().getAllowedMovies();

        if (filters.getSort() != null) {
            if (filters.getSort().getRating() != null) {
                sortByRating(filters);
            }

            if (filters.getSort().getDuration() != null) {
                sortByDuration(filters);
            }
        }

        if (filters.getContains() != null) {
            if (filters.getContains().getActors() != null) {
                filterByActor(actionInput.getFilters().getContains().getActors());
            }

            if (filters.getContains().getGenre() != null) {
                filterByGenre(actionInput.getFilters().getContains().getGenre());
            }
        }

        // Add successful output.
        Database.getInstance().addOutput();
    }
}
