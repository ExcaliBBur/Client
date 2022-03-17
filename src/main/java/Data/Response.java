package Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Data transfer class.
 */
public class Response implements Serializable {
    private final String path;
    private final ArrayList<Command.CommandData> commandData;
    private final String message;
    private final boolean isRequest;
    private final boolean isFail;

    /**
     * Constructor, gets all necessary things.
     *
     * @param path        path to file
     * @param commandData all necessary command data
     * @param message    message
     * @param isRequest   shows that someone need some data back
     * @param isFail      shows that something goes wrong
     */
    public Response(String path, ArrayList<Command.CommandData> commandData, String message, boolean isRequest,
                    boolean isFail) {
        this.path = path;
        this.commandData = commandData;
        this.message = message;
        this.isRequest = isRequest;
        this.isFail = isFail;
    }

    public ArrayList<Command.CommandData> getCommandData() {
        return commandData;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRequest() {
        return isRequest;
    }

    public boolean isFail() {
        return isFail;
    }

    /**
     * Class to form message with right order of arguments.
     */
    public static class ArgumentFormer {
        /**
         * Forms message with right order of arguments
         *
         * @param arguments arguments to work with
         * @param data      users input
         * @param object    serialized object
         * @return message
         */
        public String formMessage(String arguments, String data, String object) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] array = arguments.split(" ");

            if (array[0].equals("OBJECT")) {
                stringBuilder.append(object);
            } else {
                stringBuilder.append(data).append("\n").append(object);
            }
            return stringBuilder.toString();
        }
    }
}
