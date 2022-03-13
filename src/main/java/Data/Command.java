package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {
    private final String name;
    private final Argument firstArgument;
    private final Argument secondArgument;
    private final String description;

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

    public ArrayList<String> parseArguments(String arguments) {
        return new ArrayList<>(Arrays.asList(arguments.split("\n")));
    }

    public abstract String doOption(String arguments);

    @Override
    public String toString() {
        return this.getName() + ": " + this.getDescription() + "\n";
    }

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
