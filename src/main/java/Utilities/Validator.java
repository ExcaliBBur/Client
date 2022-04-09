package Utilities;

import Data.Command;
import Exceptions.InputException;

import java.util.ArrayList;

/**
 * Class for validating data.
 */
public class Validator {
    /**
     * Validates data.
     *
     * @param requirements things to compare with
     * @return result of compare
     * @throws InputException caused by incorrect input
     */
    public static boolean validate(ArrayList<Command.Argument> requirements, ArrayList<String> arguments)
            throws InputException {
        if (requirements.size() == 0 && arguments == null) {
            return true;
        } else if (requirements.size() != arguments.size()) {
            throw new InputException.IncorrectRequirementsException();
        } else {
            for (int i = 0; i < requirements.size(); i++) {
                if (requirements.get(i) == Command.Argument.NUMBER) {
                    try {
                        Integer.parseInt(arguments.get(i));
                    } catch (NumberFormatException e) {
                        throw new InputException.IncorrectRequirementsException();
                    }
                }
            }
        }
        return true;
    }
}
