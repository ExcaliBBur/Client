package Processing;

import Data.*;
import Interfaces.IFormer;
import Exceptions.InputException;
import Utilities.Serializer;
import Utilities.Validator;

/**
 * Class to form response.
 *
 * @param <T> type of objects to work with
 */
public class Executioner<T extends Collectables> {
    private User userToCheck;
    private User confirmedUser;

    private final IFormer<User> userFormer;
    private final IFormer<T> iFormer;
    private final CommandManager commandManager;

    /**
     * Constructor, gets all necessary things.
     *
     * @param iFormer        object former
     * @param commandManager command data
     */
    public Executioner(IFormer<User> userFormer, IFormer<T> iFormer, CommandManager commandManager) {
        this.userFormer = userFormer;
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
    public ClientDTO execute(String line, boolean isRequest) throws InputException {
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
            return new ClientDTO(line);
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
                            responseArguments = new ClientDTO.ArgumentFormer().formMessage(this.getCommandManager()
                                    .getCommands().get(command).getArgs(), arguments, object);
                        } else if (this.getCommandManager().getCommands().get(command).getArgs().contains("USER")) {
                            this.setUserToCheck(userFormer.formObj());
                            responseArguments = this.getUserToCheck().toString();
                        } else if (arguments != null) {
                            responseArguments = arguments;
                        }

                        if (userToCheck != null || confirmedUser != null) {
                            return new ClientDTO(new Command.CommandData(command,
                                    responseArguments, null), confirmedUser);
                        } else {
                            throw new InputException.NotAuthorizedException();
                        }
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

    public User getUserToCheck() {
        return userToCheck;
    }

    public void setUserToCheck(User userToCheck) {
        this.userToCheck = userToCheck;
    }

    public void setConfirmedUser(User confirmedUser) {
        this.confirmedUser = confirmedUser;
    }
}
