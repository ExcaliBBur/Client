package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents all specified classes with unique purposes.
 */
public abstract class Command {
    private final String name;
    private ArrayList<Argument> arguments;
    private final String description;

    /**
     * Constructor, gets all necessary thins.
     *
     * @param name        command name
     * @param description command description
     */
    public Command(String name, ArrayList<Argument> arguments, String description) {
        this.name = name;
        this.arguments = arguments;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Argument> getArguments() {
        return arguments;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Divides command arguments
     *
     * @return divided arguments
     */
    public ArrayList<String> parseArguments(String arguments) {
        return new ArrayList<>(Arrays.asList(arguments.split("\n")));
    }

    /**
     * Does some stuff.
     *
     * @param arguments input parameters
     * @return message
     */
    public abstract String doOption(ArrayList<String> arguments);

    @Override
    public String toString() {
        return this.getName() + ": " + this.getDescription() + "\n";
    }

    /**
     * Represents all kinds of arguments.
     */
    public enum Argument {
        NUMBER("number"),
        STRING("string"),
        NONE("none"),
        OBJECT("object"),
        USER("user");

        private final String name;

        Argument(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Represents additional data for transfer.
     */
    public static class CommandData implements Serializable {
        private final String name;
        private ArrayList<Argument> args;
        private String description;
        private ArrayList<String> userArgs;

        public CommandData(String name, ArrayList<Argument> args, String description, ArrayList<String> userArgs) {
            this.name = name;
            this.args = args;
            this.description = description;
            this.userArgs = userArgs;
        }

        public CommandData(String name, ArrayList<Argument> args, String description) {
            this.name = name;
            this.args = args;
            this.description = description;
        }

        public CommandData(String name, ArrayList<String> userArgs) {
            this.name = name;
            this.userArgs = userArgs;
        }

        public String getName() {
            return name;
        }

        public ArrayList<Argument> getArgs() {
            return args;
        }

        public String getDescription() {
            return description;
        }

        public ArrayList<String> getUserArgs() {
            return userArgs;
        }

        @Override
        public String toString() {
            return this.getName() + ": " + this.getDescription();
        }
    }
}
