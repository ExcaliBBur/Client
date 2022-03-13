package Processing;

import Data.Collectables;
import Data.Command;
import Data.Response;
import Interaction.Parser;
import Interaction.ResponseModule;
import Interfaces.IFormer;
import Exceptions.InputException;
import Processing.CommandManager;
import Utilities.Serializer;
import Utilities.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Executioner<T extends Collectables> {
    private final OperationManager operationManager;
    private final IFormer<T> iFormer;
    private final CommandManager commandManager;
    private final Validator validator;
    private final ResponseModule responseModule;

    public Executioner(OperationManager operationManager, IFormer<T> iFormer, CommandManager commandManager,
                       Validator validator, ResponseModule responseModule) {
        this.operationManager = operationManager;
        this.iFormer = iFormer;
        this.commandManager = commandManager;
        this.validator = validator;
        this.responseModule = responseModule;
    }

    public boolean execute() throws InputException, IOException {
        String line = operationManager.getLine();
        String command;
        String arguments;

        try {
            command = this.extractCommand(line).trim();
            arguments = this.extractArg(line, command);
        } catch (IndexOutOfBoundsException e) {
            command = line.trim();
            arguments = null;
        }

        if (commandManager.getCommands().containsKey(command)) {
            String argus = commandManager.getCommands().get(command).getArgs();
            if (validator.validate(argus.substring(0, argus.indexOf(" ")), arguments)) {
                if (commandManager.getClientCommands().containsKey(command)) {
                    System.out.println(commandManager.getClientCommands().get(command).doOption(arguments));
                    return false;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (argus.contains("OBJECT")) {
                        String object = new Serializer().serialize(this.getIFormer().formObj());
                        String[] array = argus.split(" ");

                        if (array[0].equals("OBJECT")) {
                            stringBuilder.append(object).append("\n");
                        } else {
                            stringBuilder.append(arguments).append("\n").append(object);
                        }
                    } else if (arguments != null) {
                        stringBuilder.append(arguments);
                    }
                    this.getResponseModule().sendResponse(Parser.parseTo(new Response(null,
                            new ArrayList<>(Collections.singletonList(new Command.CommandData(command,
                                    stringBuilder.toString(), null))), null)));
                    return true;
                }
            }
        } else {
            throw new InputException.ThereIsNoSuchCommandException();
        }
        return false;
    }

    public String extractArg(String line, String command) {
        return line.replace((command + " "), "");
    }

    public String extractCommand(String line) throws IndexOutOfBoundsException {
        return line.substring(0, line.indexOf(" "));
    }

    public OperationManager getOperationManager() {
        return operationManager;
    }

    public IFormer<T> getIFormer() {
        return iFormer;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Validator getValidator() {
        return validator;
    }

    public ResponseModule getResponseModule() {
        return responseModule;
    }
}
