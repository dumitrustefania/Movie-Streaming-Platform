package POO_TV;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class Result {
    private ArrayNode result;
    private final static Result instance = new Result();

    private Result() {
        ObjectMapper objectMapper = new ObjectMapper();
        this.result = objectMapper.createArrayNode();
    }

    public static Result getInstance() {
        return instance;
    }

    public ArrayNode getResult() {
        return result;
    }

    public void setResult(ArrayNode result) {
        this.result = result;
    }
}
