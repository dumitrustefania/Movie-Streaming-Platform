package platform.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public final class Result {
    private final ArrayNode result;
    private final static Result INSTANCE = new Result();

    /**
     * Singleton implementation of the result.
     */
    private Result() {
        ObjectMapper objectMapper = new ObjectMapper();
        this.result = objectMapper.createArrayNode();
    }

    public static Result getInstance() {
        return INSTANCE;
    }

    public ArrayNode getResult() {
        return result;
    }
}
