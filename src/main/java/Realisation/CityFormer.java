package Realisation;

import Models.City;
import Interfaces.IFormer;
import Exceptions.InputException;

import java.util.Scanner;

/**
 * Class that implements the creation of a new object of the Data.City class.
 */
public class CityFormer implements IFormer<City> {
    private final Scanner scanner;

    public CityFormer(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Starts forming cycle of new Data.City object.
     *
     * @return new Data.City object for collection.
     */
    public City formObj() {
        City city = new City();
        boolean repeat;

        do {
            repeat = false;
            try {
                if (scanner.hasNextLine()) city.setInputName(scanner.nextLine().replaceAll(" ", "_"));
            } catch (InputException e) {
                repeat = true;
            }
        } while (repeat);

        city.setCoordinates(new City.Coordinates());
        do {
            repeat = false;
            try {
                if (scanner.hasNextLine())
                    city.getCoordinates().setInputFirstCoordinates(scanner.nextLine());
            } catch (InputException e) {
                repeat = true;
            }
        } while (repeat);

        do {
            repeat = false;
            try {
                if (scanner.hasNextLine())
                    city.getCoordinates().setInputSecondCoordinates(scanner.nextLine());
            } catch (InputException e) {
                repeat = true;
            }
        } while (repeat);

        do {
            repeat = false;
            try {
                if (scanner.hasNextLine()) city.setInputArea(scanner.nextLine());
            } catch (InputException e) {
                repeat = true;
            }
        } while (repeat);

        do {
            repeat = false;
            try {
                if (scanner.hasNextLine()) city.setInputPopulation(scanner.nextLine());
            } catch (InputException e) {
                repeat = true;
            }
        } while (repeat);

        do {
            repeat = false;
            try {
                if (scanner.hasNextLine()) city.setInputMeters(scanner.nextLine());
            } catch (InputException e) {
                repeat = true;
            }
        } while (repeat);

        do {
            repeat = false;
            try {
                if (scanner.hasNextLine()) city.setInputClimate(scanner.nextLine());
            } catch (InputException e) {
                repeat = true;
            }
        } while (repeat);

        do {
            repeat = false;
            try {
                if (scanner.hasNextLine()) city.setInputGovernment(scanner.nextLine());
            } catch (InputException e) {
                repeat = true;
            }
        } while (repeat);

        do {
            repeat = false;
            try {
                if (scanner.hasNextLine()) city.setInputStandardOfLiving(scanner.nextLine());
            } catch (InputException e) {
                repeat = true;
            }
        } while (repeat);

        city.setGovernor(new City.Human());

        do {
            repeat = false;
            try {
                if (scanner.hasNextLine()) city.getGovernor().setInputHumanName(scanner.nextLine()
                        .replaceAll(" ", "_"));
            } catch (InputException e) {
                repeat = true;
            }
        } while (repeat);

        return city;
    }
}
