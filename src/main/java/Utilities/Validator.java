package Utilities;

import Exceptions.InputException;

public class Validator {
    public boolean validate(String requirements, String argument) throws InputException {
        switch (requirements) {
            case "NONE": {
                if (argument == null || argument.equals("")) {
                    return true;
                } else {
                    throw new InputException.IncorrectOptionException();
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
