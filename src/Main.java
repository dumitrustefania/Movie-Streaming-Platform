import POO_TV.Database;
import POO_TV.Movie;
import POO_TV.Result;
import POO_TV.actions.*;
import POO_TV.actions.Action;
import POO_TV.fileio.ActionInput;
import POO_TV.fileio.Input;
import POO_TV.fileio.Output;
import POO_TV.pages.Page;
import POO_TV.pages.UnauthenticatedHomepage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String argss = new String("checker/resources/in/basic_4.json");
//        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        Input inputData = objectMapper.readValue(new File(argss), Input.class);

        Database database = Database.getInstance();
        database.setUsers(inputData.getUsers());
        database.setMovies(inputData.getMovies());
        database.setCurrentPage(new UnauthenticatedHomepage());
        database.setCurrentUserMovies(new ArrayList<Movie>());

        for(ActionInput actionInput : inputData.getActions()) {
            Page currentPage = Database.getInstance().getCurrentPage();
            if(actionInput.getType().equals("change page")) {
                if(!currentPage.getAllowedNextPages().contains(actionInput.getPage())) {
                    Database.getInstance().addErrorOutput();
                    continue;
                }
                currentPage.execute(new ChangePageAction(actionInput));
            } else {
                if (!currentPage.getAllowedActions().contains(actionInput.getFeature())) {
                    Database.getInstance().addErrorOutput();
                    continue;
                }

                switch (actionInput.getFeature()) {
                    case "login" -> currentPage.execute(new LoginAction(actionInput));
                    case "register" -> currentPage.execute(new RegisterAction(actionInput));
                    case "search" -> currentPage.execute(new SearchAction(actionInput));
                    case "filter" -> currentPage.execute(new FilterAction(actionInput));
                }
            }
        }

        ArrayNode outputData = Result.getInstance().getResult();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
//        objectWriter.writeValue(new File(args[1]), outputData);
        objectWriter.writeValue(new File(argss + ".out"), outputData);

    }
}
