package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerDTO implements Serializable {
    private final byte[] message;
    private ArrayList<Command.CommandData> commandData;
    private boolean isSuccess;
    private boolean isTerminating;

    public ServerDTO(byte[] message, ArrayList<Command.CommandData> commandData, boolean isSuccess,
                     boolean isTerminating) {
        this.message = message;
        this.commandData = commandData;
        this.isSuccess = isSuccess;
        this.isTerminating = isTerminating;
    }

    public ServerDTO(byte[] message, boolean isSuccess, boolean isTerminating) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.isTerminating = isTerminating;
    }

    public ServerDTO(byte[] message, ArrayList<Command.CommandData> commandData) {
        this.message = message;
        this.commandData = commandData;
    }

    public byte[] getMessage() {
        return message;
    }

    public ArrayList<Command.CommandData> getCommandData() {
        return commandData;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isTerminating() {
        return isTerminating;
    }
}
