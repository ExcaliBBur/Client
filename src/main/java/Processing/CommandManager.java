package Processing;

import Data.Command;
import Main.Client;
import Utilities.TextReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Class for working with various local or server commands.
 */
public class CommandManager {
    private final TreeMap<String, Command.CommandData> commands = new TreeMap<>();
    private final TreeMap<String, Command> clientCommands = new TreeMap<>();

    /**
     * Constructor, initialises server command data.
     *
     * @param commandData server command data
     */
    public CommandManager(ArrayList<Command.CommandData> commandData) {
        commandData.forEach(x -> this.getCommands().put(x.getName(), x));
        this.setClientCommands();
        this.getClientCommands().forEach((x, y) -> this.getCommands().put(y.getName(),
                new Command.CommandData(y.getName(), y.getFirstArgument()
                        + " " + y.getSecondArgument(), y.getDescription())));
    }

    public TreeMap<String, Command.CommandData> getCommands() {
        return commands;
    }

    public TreeMap<String, Command> getClientCommands() {
        return clientCommands;
    }

    public void setClientCommands() {
        this.getClientCommands().put("help", new Help());
        this.getClientCommands().put("execute_script", new ExecuteScript());
        this.getClientCommands().put("exit", new Exit());
    }

    /**
     * Help command realization with additional data.
     */
    public class Help extends Command {
        public Help() {
            super("help", Argument.NONE, Argument.NONE, "displays a description of all " +
                    "commands available to the user");
        }

        public String doOption(String arguments) {
            StringBuilder stringBuilder = new StringBuilder();

            CommandManager.this.getCommands().forEach((x, y) -> stringBuilder.append(y.toString()).append("\n"));
            return stringBuilder.toString();
        }
    }

    /**
     * ExecuteScript command realization with additional data.
     */
    public static class ExecuteScript extends Command {
        public ExecuteScript() {
            super("execute_script", Argument.STRING, Argument.NONE, "Starts the script execution");
        }

        public String doOption(String arguments) {
            try {
                String[] commands = TextReader.readFile(new File(arguments)).split("\n");

                Arrays.stream(commands).forEach(command -> OperationManager.getQueue().add(command));
            } catch (NullPointerException | IOException e) {
                System.out.println("There is no such file.");
            }
            return "";
        }
    }

    /**
     * Exit command realization with additional data.
     */
    public static class Exit extends Command {
        public Exit() {
            super("exit", Argument.NONE, Argument.NONE, "Ends the execution" +
                    " of the program");
        }

        public String doOption(String arguments) {
            Client.setFinished(true);

            return "The execution of the program is completed";
        }
    }
}
