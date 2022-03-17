package Main;

import Data.*;
import Exceptions.InputException;
import Interaction.Parser;
import Interaction.Receiver;
import Interaction.Sender;
import Processing.*;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
            InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName("localhost"), 6666);

            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);
            OperationManager operationManager = new OperationManager(new Scanner(System.in));

            System.out.println("Type something to start.");
            sender.sendResponse(Parser.parseTo(new Response(operationManager.getLine(),
                    null, null, true, false)), inetSocketAddress);

            Response response = (Response) Objects.requireNonNull(Parser.parseFrom(receiver.getResponse()));
            System.out.println(response.getMessage());
            boolean isRequested = response.isRequest();
            CommandManager commandManager = new CommandManager(response.getCommandData());

            CityFormer cityFormer = new CityFormer(operationManager);
            Executioner<City> executioner = new Executioner<>(cityFormer, commandManager);

            while (!Client.isFinished()) {
                try {
                    response = executioner.execute(operationManager.getLine(), isRequested);
                    if (response != null) {
                        sender.sendResponse(Parser.parseTo(response), inetSocketAddress);
                        response = (Response) Objects.requireNonNull(Parser.parseFrom(receiver
                                .getResponse()));
                        System.out.println(response.getMessage());
                        isRequested = response.isRequest();

                        if (response.isFail()) {
                            System.out.println("Try again with restarting the program.");
                            break;
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