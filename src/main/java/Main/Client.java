package Main;

import Data.*;
import Exceptions.InputException;
import Interaction.Parser;
import Interaction.ResponseModule;
import Processing.CityFormer;
import Processing.CommandManager;
import Processing.Executioner;
import Processing.OperationManager;
import Utilities.Validator;

import java.io.*;
import java.net.InetAddress;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    private static boolean finished;

    public static void main(String[] args) {
        try {
            ResponseModule responseModule = new ResponseModule(6667, InetAddress.getByName("localhost"));
            OperationManager operationManager = new OperationManager(new Scanner(System.in));

            System.out.println("Enter the name of the environment variable.");
            responseModule.sendResponse(Parser.parseTo(new Response(operationManager.getLine(),
                    null, null)));
            System.out.println(((Response) Objects.requireNonNull(Parser
                    .parseFrom(responseModule.getResponse()))).getResponse());

            CommandManager commandManager = new CommandManager(((Response) Objects.requireNonNull(Parser
                    .parseFrom(responseModule
                            .getResponse()))).getCommandData());

            Validator validator = new Validator();
            CityFormer cityFormer = new CityFormer(operationManager);
            Executioner<City> executioner = new Executioner<>(operationManager, cityFormer, commandManager,
                    validator, responseModule);

            while (!Client.isFinished()) {
                try {
                    if (executioner.execute()) {
                        System.out.println(((Response) Objects.requireNonNull(Parser.parseFrom(responseModule
                                .getResponse()))).getResponse());
                    }
                } catch (InputException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            System.out.println("The program is terminated due to an error.");
        }
    }

    public static boolean isFinished() {
        return finished;
    }

    public static void setFinished(boolean finished) {
        Client.finished = finished;
    }
}