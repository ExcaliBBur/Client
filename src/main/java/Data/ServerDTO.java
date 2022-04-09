package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerDTO implements Serializable {
    private String message;
    ArrayList<Command.CommandData> commandData;
    private boolean isSuccess;

    public ServerDTO(String message, ArrayList<Command.CommandData> commandData, boolean isSuccess) {
        this.message = message;
        this.commandData = commandData;
        this.isSuccess = isSuccess;
    }

    public ServerDTO(String message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public ServerDTO(String message, ArrayList<Command.CommandData> commandData) {
        this.message = message;
        this.commandData = commandData;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Command.CommandData> getCommandData() {
        return commandData;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
