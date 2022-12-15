import platform.Database;
import platform.Movie;
import platform.Result;
import platform.fileio.ActionInput;
import platform.fileio.Input;
import platform.pages.Page;
import platform.actions.ActionFactory;
import platform.pages.UnauthenticatedHomepage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    /**
     * @param inputData
     */
    private static void executeActions(final Input inputData) {
        for (ActionInput actionInput : inputData.getActions()) {
            Page currentPage = Database.getInstance().getCurrentPage();
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
            currentPage.execute(ActionFactory.createAction(actionInput));
        }
    }

    /**
     * @param inputData
     */
    private static void initDatabase(final Input inputData) {
        Database database = Database.getInstance();
        database.setUsers(inputData.getUsers());
        database.setMovies(inputData.getMovies());
        database.setCurrentPage(new UnauthenticatedHomepage());
        database.setCurrentUserMovies(new ArrayList<Movie>());
    }


    /**
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
        objectWriter.writeValue(new File(args[1]), outputData);
    }
}
