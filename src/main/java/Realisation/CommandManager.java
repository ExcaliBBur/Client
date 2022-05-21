package Realisation;

import Models.Command;
import Main.Client;
import Utilities.TextReader;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Class for working with various local or server commands.
 */
public class CommandManager {
    private final TreeMap<String, Command.CommandData> commands = new TreeMap<>();

    /**
     * Constructor, initialises server command data.
     *
     * @param commandData server command data
     */
    public CommandManager(List<Command.CommandData> commandData) {
        commandData.forEach(x -> this.getCommands().put(x.getName(), x));
    }

    public TreeMap<String, Command.CommandData> getCommands() {
        return commands;
    }
}
