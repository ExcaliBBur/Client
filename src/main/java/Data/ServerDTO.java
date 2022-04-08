package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerDTO implements Serializable {
    private String message;
    ArrayList<Command.CommandData> commandData;
    private boolean isRequest;
    private boolean isFail;
    private boolean isSuccess;

    public ServerDTO(String message, ArrayList<Command.CommandData> commandData, boolean isRequest, boolean isFail,
                     boolean isSuccess) {
        this.message = message;
        this.commandData = commandData;
        this.isRequest = isRequest;
        this.isFail = isFail;
        this.isSuccess = isSuccess;
    }

    public ServerDTO(String message, boolean isFail, boolean isSuccess) {
        this.message = message;
        this.isFail = isFail;
        this.isSuccess = isSuccess;
    }

    public ServerDTO(String message, ArrayList<Command.CommandData> commandData, boolean isRequest) {
        this.message = message;
        this.commandData = commandData;
        this.isRequest = isRequest;
    }

    public ServerDTO(boolean isFail) {
        this.isFail = isFail;
    }

    public boolean isRequest() {
        return isRequest;
    }

    public String getMessage() {
        return message;
    }

    public boolean isFail() {
        return isFail;
    }

    public ArrayList<Command.CommandData> getCommandData() {
        return commandData;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
