package Data;

import java.io.Serializable;

public class ClientDTO implements Serializable {
    private String path;
    private Command.CommandData commandData;
    private boolean isRequest;
    private User user;

    public ClientDTO( String path, Command.CommandData commandData, boolean isRequest, User user) {
        this.path = path;
        this.commandData = commandData;
        this.isRequest = isRequest;
        this.user = user;
    }

    public ClientDTO(Command.CommandData commandData, User user) {
        this.commandData = commandData;
        this.user = user;
    }

    public ClientDTO(boolean isRequest) {
        this.isRequest = isRequest;
    }

    public ClientDTO(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public Command.CommandData getCommandData() {
        return commandData;
    }

    public boolean isRequest() {
        return isRequest;
    }

    public User getUser() {
        return user;
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
