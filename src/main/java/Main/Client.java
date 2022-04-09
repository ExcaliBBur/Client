package Main;

import Data.*;
import Exceptions.InputException;
import Interaction.Parser;
import Interaction.Receiver;
import Interaction.Sender;
import Interfaces.IFormer;
import Processing.*;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main class
 */
public class Client {
    private static boolean finished;

    public static void main(String[] args) {
        Registrator registrator = new Registrator(new Scanner(System.in));
        try (DatagramSocket socket = registrator.register()) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 6666);

            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);
            OperationManager operationManager = new OperationManager(new Scanner(System.in));

            sender.sendResponse(Parser.parseTo(new ClientDTO(true)), inetSocketAddress);

            ServerDTO serverDTO = (ServerDTO) Objects.requireNonNull(Parser.parseFrom(receiver.getResponse()));
            System.out.println(serverDTO.getMessage());
            CommandManager commandManager = new CommandManager(serverDTO.getCommandData());

            CityFormer cityFormer = new CityFormer(operationManager);
            IFormer<User> userFormer = new UserFormer(operationManager);
            Executioner<City> executioner = new Executioner<>(userFormer, cityFormer, commandManager);

            while (!Client.isFinished()) {
                try {
                    ClientDTO clientDTO = executioner.execute(operationManager.getLine());
                    if (clientDTO != null) {
                        sender.sendResponse(Parser.parseTo(clientDTO), inetSocketAddress);
                        serverDTO = (ServerDTO) Objects.requireNonNull(Parser.parseFrom(receiver
                                .getResponse()));
                        System.out.println(serverDTO.getMessage());

                        if (executioner.getUserToCheck() != null) {
                            if (serverDTO.isSuccess()) {
                                executioner.setConfirmedUser(executioner.getUserToCheck());
                            }
                            executioner.setUserToCheck(null);
                        }
                    }
                } catch (InputException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (InputException.ServerUnavailableException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again later with restarting the program.");
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

    public static class Registrator {
        private final Scanner scanner;

        public Registrator(Scanner scanner) {
            this.scanner = scanner;
        }

        public DatagramSocket register() {
            boolean isCorrect;
            DatagramSocket datagramSocket = null;
            System.out.println("Enter the port.");
            do {
                isCorrect = false;
                try {
                    datagramSocket = new DatagramSocket(Integer.parseInt(scanner.nextLine()));
                    isCorrect = true;
                } catch (SocketException e) {
                    System.out.println("The entered port is employed, try again.");
                } catch (NumberFormatException e) {
                    System.out.println("Error, need to be integer, try again.");
                }
            } while (!isCorrect);
            return datagramSocket;
        }
    }
}