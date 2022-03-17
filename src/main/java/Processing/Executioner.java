package Processing;

import Data.Collectables;
import Data.Command;
import Data.Response;
import Interfaces.IFormer;
import Exceptions.InputException;
import Utilities.Serializer;
import Utilities.Validator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to form response.
 *
 * @param <T> type of objects to work with
 */
public class Executioner<T extends Collectables> {
    private final IFormer<T> iFormer;
    private final CommandManager commandManager;

    /**
     * Constructor, gets all necessary things.
     *
     * @param iFormer        object former
     * @param commandManager command data
     */
    public Executioner(IFormer<T> iFormer, CommandManager commandManager) {
        this.iFormer = iFormer;
        this.commandManager = commandManager;
    }

    /**
     * Forms response.
     *
     * @param line      user input
     * @param isRequest sign how to react
     * @return response to send
     * @throws InputException caused by working with user input
     */
    public Response execute(String line, boolean isRequest) throws InputException {
        String command;
        String arguments;

        try {
            command = this.extractCommand(line).trim();
            arguments = this.extractArg(line, command);
        } catch (IndexOutOfBoundsException e) {
            command = line.trim();
            arguments = null;
        }

        if (isRequest) {
            return new Response(line, null, null, false, false);
        } else {
            if (commandManager.getCommands().containsKey(command)) {
                if (Validator.validate(this.getCommandManager().getCommands().get(command).getArgs(), arguments)) {
                    if (commandManager.getClientCommands().containsKey(command)) {
                        System.out.println(commandManager.getClientCommands().get(command).doOption(arguments));
                        return null;
                    } else {
                        String responseArguments = "";
                        if (this.getCommandManager().getCommands().get(command).getArgs().contains("OBJECT")) {
                            String object = Serializer.serialize(this.getIFormer().formObj());
                            responseArguments = new Response.ArgumentFormer().formMessage(this.getCommandManager()
                                    .getCommands().get(command).getArgs(), arguments, object);
                        } else if (arguments != null) {
                            responseArguments = arguments;
                        }
                        return new Response(null,
                                new ArrayList<>(Collections.singletonList(new Command.CommandData(command,
                                        responseArguments, null))), null, false, false);
                    }
                } else {
                    throw new InputException.ArgumentsException();
                }
            } else {
                throw new InputException.ThereIsNoSuchCommandException();
            }
        }
    }

    public String extractArg(String line, String command) {
        return line.replace((command + " "), "");
    }

    public String extractCommand(String line) throws IndexOutOfBoundsException {
        return line.substring(0, line.indexOf(" "));
    }

    public IFormer<T> getIFormer() {
        return iFormer;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
