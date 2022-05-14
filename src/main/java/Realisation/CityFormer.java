package Realisation;

import Models.City;
import Interfaces.IFormer;
import Exceptions.InputException;

/**
 * Class that implements the creation of a new object of the Data.City class.
 */
public class CityFormer implements IFormer<City> {
    private final OperationManager operationManager;

    public CityFormer(OperationManager operationManager) {
        this.operationManager = operationManager;
    }

    /**
     * Getter of operationManager.
     *
     * @return operation manager
     */
    public OperationManager getOperationManager() {
        return operationManager;
    }


    /**
     * Starts forming cycle of new Data.City object.
     *
     * @return new Data.City object for collection.
     */
    public City formObj() {
        City city = new City();
        boolean repeat;

        System.out.println("Enter the name of the city (String, can't be null, line can't be empty).");
        do {
            repeat = false;
            try {
                city.setInputName(getOperationManager().getLine().replaceAll(" ", "_"));
            } catch (InputException e) {
                System.out.println(e.getMessage());
                repeat = true;
            }
        } while (repeat);

        System.out.println("Enter the coordinates of the city.");
        city.setCoordinates(new City.Coordinates());
        System.out.println("Enter the X-coordinates (Double, can't be null, maximum value is not more than 627).");
        do {
            repeat = false;
            try {
                city.getCoordinates().setInputFirstCoordinates(getOperationManager().getLine());
            } catch (InputException e) {
                System.out.println(e.getMessage());
                repeat = true;
            }
        } while (repeat);

        System.out.println("Enter the Y-coordinates (Float, can't be null).");
        do {
            repeat = false;
            try {
                city.getCoordinates().setInputSecondCoordinates(getOperationManager().getLine());
            } catch (InputException e) {
                System.out.println(e.getMessage());
                repeat = true;
            }
        } while (repeat);

        System.out.println("Enter the area of the city (Integer, must be greater than zero," +
                " can't be null).");
        do {
            repeat = false;
            try {
                city.setInputArea(getOperationManager().getLine());
            } catch (InputException e) {
                System.out.println(e.getMessage());
                repeat = true;
            }
        } while (repeat);

        System.out.println("Enter the population of the city (Integer, must be greater than zero," +
                " can't be null).");
        do {
            repeat = false;
            try {
                city.setInputPopulation(getOperationManager().getLine());
            } catch (InputException e) {
                System.out.println(e.getMessage());
                repeat = true;
            }
        } while (repeat);

        System.out.println("Enter the height above sea level of the city (int).");
        do {
            repeat = false;
            try {
                city.setInputMeters(getOperationManager().getLine());
            } catch (InputException e) {
                System.out.println(e.getMessage());
                repeat = true;
            }
        } while (repeat);

        System.out.println("Enter the type of climate of the city (can be null).");
        System.out.println("You have a choice: MONSOON, OCEANIC, MEDITERRANIAN, TUNDRA.");
        do {
            repeat = false;
            try {
                city.setInputClimate(getOperationManager().getLine());
            } catch (InputException e) {
                System.out.println(e.getMessage());
                repeat = true;
            }
        } while (repeat);

        System.out.println("Enter the type of government of the city (can be null).");
        System.out.println("You have a choice: MATRIARCHY, OLIGARCHY, PLUTOCRACY, JUNTA.");
        do {
            repeat = false;
            try {
                city.setInputGovernment(getOperationManager().getLine());
            } catch (InputException e) {
                System.out.println(e.getMessage());
                repeat = true;
            }
        } while (repeat);

        System.out.println("Enter the standard of life of the city (can be null).");
        System.out.println("You have a choice: ULTRA_HIGH, VERY_HIGH, MEDIUM, LOW, ULTRA_LOW.");
        do {
            repeat = false;
            try {
                city.setInputStandardOfLiving(getOperationManager().getLine());
            } catch (InputException e) {
                System.out.println(e.getMessage());
                repeat = true;
            }
        } while (repeat);

        city.setGovernor(new City.Human());

        System.out.println("Enter the name of the city governor (String, can't be null).");
        do {
            repeat = false;
            try {
                city.getGovernor().setInputHumanName(getOperationManager().getLine()
                        .replaceAll(" ", "_"));
            } catch (InputException e) {
                System.out.println(e.getMessage());
                repeat = true;
            }
        } while (repeat);

        return city;
    }
}
