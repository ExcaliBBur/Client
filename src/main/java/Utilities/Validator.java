package Utilities;

import Exceptions.InputException;

/**
 * Class for validating data.
 */
public class Validator {
    /**
     * Validates data.
     *
     * @param requirements things to compare with
     * @param argument     things to check
     * @return result of compare
     * @throws InputException caused by incorrect input
     */
    public static boolean validate(String requirements, String argument) throws InputException {
        String[] separated = requirements.split(" ");
        switch (separated[0]) {
            case "NONE": {
                if (argument == null || argument.equals("")) {
                    return true;
                } else {
                    throw new InputException.IncorrectRequirementsException();
                }
            }
            case "NUMBER": {
                try {
                    Integer.parseInt(argument);
                } catch (NumberFormatException e) {
                    throw new InputException.IncorrectRequirementsException();
                }
            }
        }
        return true;
    }
}
