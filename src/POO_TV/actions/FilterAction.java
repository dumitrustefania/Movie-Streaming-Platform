package POO_TV.actions;

import POO_TV.Database;
import POO_TV.Movie;
import POO_TV.fileio.ActionInput;
import POO_TV.fileio.FilterInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FilterAction extends Action{
    public FilterAction(ActionInput actionInput) {
        super(actionInput);
    }

    @Override
    public void execute() {
        System.out.println("Executing filter action.");
        FilterInput filters = actionInput.getFilters();
        if(filters.getSort() != null) {
            if(filters.getSort().getDuration() != null)
                Database.getInstance().setCurrentUserMovies(Database.getInstance().getCurrentUserMovies()
                        .stream()
                        .sorted(new Comparator<Movie>() {
                            @Override
                            public int compare(Movie o1, Movie o2) {
                                if(filters.getSort().getDuration().equals("increasing"))
                                    return Integer.compare(o1.getDuration(), o2.getDuration());
                                else
                                    return Integer.compare(o2.getDuration(), o1.getDuration());
                            }
                        })
                        .collect(Collectors.toCollection(ArrayList::new)));

            if(filters.getSort().getRating() != null)
                Database.getInstance().setCurrentUserMovies(Database.getInstance().getCurrentUserMovies()
                        .stream()
                        .sorted(new Comparator<Movie>() {
                            @Override
                            public int compare(Movie o1, Movie o2) {
                                if(filters.getSort().getRating().equals("increasing"))
                                    return Double.compare(o1.getRating(), o2.getRating());
                                else
                                    return Double.compare(o2.getRating(), o1.getRating());
                            }
                        })
                        .collect(Collectors.toCollection(ArrayList::new)));
        }

//        if(filters.getContains() != null) {
//
//        }


        Database.getInstance().addOutput();
    }
}
