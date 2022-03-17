package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents all specified classes with unique purposes.
 */
public abstract class Command {
    private final String name;
    private final Argument firstArgument;
    private final Argument secondArgument;
    private final String description;

    /**
     * Constructor, gets all necessary thins.
     *
     * @param name           command name
     * @param firstArgument  first argument
     * @param secondArgument second argument
     * @param description    command description
     */
    public Command(String name, Argument firstArgument, Argument secondArgument, String description) {
        this.name = name;
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Argument getFirstArgument() {
        return firstArgument;
    }

    public Argument getSecondArgument() {
        return secondArgument;
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
    public abstract String doOption(String arguments);

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
        OBJECT("object");

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
        private final String args;
        private final String description;

        public CommandData(String name, String args, String description) {
            this.name = name;
            this.args = args;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getArgs() {
            return args;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return this.getName() + ": " + this.getDescription();
        }
    }
}
