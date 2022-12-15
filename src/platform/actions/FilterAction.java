package platform.actions;

import platform.Database;
import platform.Movie;
import platform.fileio.ActionInput;
import platform.fileio.FilterInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class FilterAction extends Action {
    public FilterAction(final ActionInput actionInput) {
        super(actionInput);
    }

    /**
     * @param filter
     */
    public void sortByMovie(final FilterInput filter) {
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
     * @param filter
     */
    public void sortByRating(final FilterInput filter) {
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
     * @param genres
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
     * @param actors
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
        Database.getInstance().getCurrentUser().getAllowedMovies();

        if (filters.getSort() != null) {
            if (filters.getSort().getRating() != null) {
                sortByMovie(filters);
            }

            if (filters.getSort().getDuration() != null) {
                sortByRating(filters);
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

        Database.getInstance().addOutput();
    }
}
