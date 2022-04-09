package Processing;

import Data.Command;
import Main.Client;
import Utilities.TextReader;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Class for working with various local or server commands.
 */
public class CommandManager {
    private final TreeMap<String, Command.CommandData> commands = new TreeMap<>();
    private final TreeMap<String, Command> clientCommands;

    /**
     * Constructor, initialises server command data.
     *
     * @param commandData server command data
     */
    public CommandManager(ArrayList<Command.CommandData> commandData) {
        commandData.forEach(x -> this.getCommands().put(x.getName(), x));
        this.clientCommands = setClientCommands();
        this.getClientCommands().forEach((x, y) -> this.getCommands().put(y.getName(),
                new Command.CommandData(y.getName(), y.getArguments(), y.getDescription(), null)));
    }

    public TreeMap<String, Command.CommandData> getCommands() {
        return commands;
    }

    public TreeMap<String, Command> getClientCommands() {
        return clientCommands;
    }

    public TreeMap<String, Command> setClientCommands() {
        TreeMap<String, Command> treeMap = new TreeMap<>();

        new Reflections().getSubTypesOf(Command.class).forEach(x -> {
            Command command;
            try {
                try {
                    command = x.getConstructor(CommandManager.class).newInstance(this);
                    treeMap.put(command.getName(), command);
                } catch (NoSuchMethodException e) {
                    command = x.getConstructor().newInstance();
                    treeMap.put(command.getName(), command);
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                    InstantiationException e) {
                e.printStackTrace();
            }
        });

        return treeMap;
    }

    /**
     * Help command realization with additional data.
     */
    public class Help extends Command {
        public Help() {
            super("help", new ArrayList<>(), "displays a description of all " +
                    "commands available to the user");
        }

        public String doOption(ArrayList<String> arguments) {
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
            super("execute_script", new ArrayList<>(Arrays.asList(Argument.STRING)),
                    "Starts the script execution");
        }

        public String doOption(ArrayList<String> arguments) {
            try {
                String[] commands = TextReader.readFile(new File(arguments.get(0))).split("\n");

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
            super("exit", new ArrayList<>(), "Ends the execution" +
                    " of the program");
        }

        public String doOption(ArrayList<String> arguments) {
            Client.setFinished(true);

            return "The execution of the program is completed";
        }
    }
}
