package Exceptions;

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
            super.message = "You entered an empty line, try again.";
        }
    }

    /**
     * Not a number.
     */
    public static class NotANumberException extends InputException {
        public NotANumberException() {
            super.message = "You didn't enter a number.";
        }
    }

    /**
     * Incorrect requirements for field.
     */
    public static class IncorrectRequirementsException extends InputException {
        public IncorrectRequirementsException() {
            super.message = "The entered line does not meet the requirements, try again.";
        }
    }

    /**
     * There is no such option.
     */
    public static class IncorrectOptionException extends InputException {
        public IncorrectOptionException() {
            super.message = "You didn't enter an option.";
        }
    }

    /**
     * Wrong arguments.
     */
    public static class ArgumentsException extends InputException {
        public ArgumentsException() {
            super.message = "Something is wrong with command arguments.";
        }
    }

    /**
     * No such command.
     */
    public static class ThereIsNoSuchCommandException extends InputException {
        public ThereIsNoSuchCommandException() {
            super.message = "There is no such command.";
        }
    }

    /**
     * Server is unavailable.
     */
    public static class ServerUnavailableException extends InputException {
        public ServerUnavailableException() {
            super.message = "The server is unavailable.";
        }
    }
}
