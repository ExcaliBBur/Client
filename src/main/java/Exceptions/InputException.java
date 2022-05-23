package Exceptions;

import Main.Client;

/**
 * Represents user input errors.
 */
public abstract class InputException extends Exception {
    private String message;

    public InputException() {

    }

    /**
     * Getter of exception message.
     *
     * @return exception message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Empty string.
     */
    public static class EmptyLineException extends InputException {
        public EmptyLineException() {
            super.message = Client.resourceFactory.getResources().getString("empty_line");
        }

        public EmptyLineException(String message) {
            super.message = Client.resourceFactory.getResources().getString(message) + ": " + Client.resourceFactory
                    .getResources().getString("empty_line");
        }
    }

    /**
     * Incorrect requirements for field.
     */
    public static class IncorrectRequirementsException extends InputException {
        public IncorrectRequirementsException() {
            super.message = Client.resourceFactory.getResources().getString("false_requirements");
        }

        public IncorrectRequirementsException(String message) {
            super.message = Client.resourceFactory.getResources().getString(message) + ": " + Client.resourceFactory
                    .getResources().getString("false_requirements");
        }
    }

    /**
     * There is no such option.
     */
    public static class IncorrectOptionException extends InputException {
        public IncorrectOptionException() {
            super.message = Client.resourceFactory.getResources().getString("not_an_option");
        }

        public IncorrectOptionException(String message) {
            super.message = Client.resourceFactory.getResources().getString(message) + ": " + Client.resourceFactory
                    .getResources().getString("not_an_option");
        }
    }

    /**
     * Wrong arguments.
     */
    public static class ArgumentsException extends InputException {
        public ArgumentsException() {
            super.message = Client.resourceFactory.getResources().getString("false_arguments");
        }
    }

    /**
     * No such command.
     */
    public static class ThereIsNoSuchCommandException extends InputException {
        public ThereIsNoSuchCommandException() {
            super.message = Client.resourceFactory.getResources().getString("not_a_command");
        }
    }

    /**
     * Server is unavailable.
     */
    public static class ServerUnavailableException extends InputException {
        public ServerUnavailableException() {
            super.message = Client.resourceFactory.getResources().getString("server_unavailable");
        }
    }

    public static class NotAuthorizedException extends InputException {
        public NotAuthorizedException() {
            super.message = Client.resourceFactory.getResources().getString("empty_account");
        }
    }
}
