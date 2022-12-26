import platform.database.Database;
import platform.database.Movie;
import platform.database.Result;
import platform.fileio.ActionInput;
import platform.fileio.Input;
import platform.pages.Page;
import platform.actions.ActionStrategyFactory;
import platform.pages.UnauthenticatedHomepage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    /**
     * Execute each action given in input.
     * @param inputData the input data parsed from input JSON
     */
    private static void executeActions(final Input inputData) {
        for (ActionInput actionInput : inputData.getActions()) {
            Page currentPage = Database.getInstance().getCurrentPage();

            // Check for errors.
            if (actionInput.getType().equals("change page")
                && !currentPage.getAllowedNextPages().contains(actionInput.getPage())) {
                    Database.getInstance().addErrorOutput();
                    continue;
                }
            if (actionInput.getType().equals("on page")
                && !currentPage.getAllowedActions().contains(actionInput.getFeature())) {
                    Database.getInstance().addErrorOutput();
                    continue;
            }

            /* Use the action factory to create the necessary action class and
               call execute() method, using the strategy pattern to
               execute each required subclass of the Action class. */
            currentPage.execute(ActionStrategyFactory.createAction(actionInput));
        }
    }

    /**
     * Initialise the platform's database with values form input.
     * @param inputData the input data parsed from input JSON
     */
    private static void initDatabase(final Input inputData) {
        Database database = Database.getInstance();
        database.setUsers(inputData.getUsers());
        database.setMovies(inputData.getMovies());
        database.setCurrentPage(new UnauthenticatedHomepage());
        database.setCurrentUserMovies(new ArrayList<Movie>());
    }


    /**
     * Main function of the platform, parses input JSON,
     * executes all the functions of the platform
     * and outputs results into JSON format.
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        ArrayNode outputData = Result.getInstance().getResult().removeAll();

        initDatabase(inputData);
        executeActions(inputData);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File("results.out"), outputData);
    }
}
