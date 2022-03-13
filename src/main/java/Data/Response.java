package Data;

import Data.Command;

import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {
    private final String path;
    private final ArrayList<Command.CommandData> commandData;
    private final String response;

    public Response(String path, ArrayList<Command.CommandData> commandData, String response) {
        this.path = path;
        this.commandData = commandData;
        this.response = response;
    }

    public ArrayList<Command.CommandData> getCommandData() {
        return commandData;
    }

    public String getPath() {
        return path;
    }

    public String getResponse() {
        return response;
    }
}
