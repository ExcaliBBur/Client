package Main;

import Controllers.LoginScreenController;
import Models.*;
import Interaction.Parser;
import Interaction.Receiver;
import Interaction.Sender;
import Realisation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Main class
 */
public class Client extends Application {
    private static boolean finished;
// пакость.
    public static void main(String[] args) {
        Application.launch();
    }

    public static boolean isFinished() {
        return finished;
    }

    public static void setFinished(boolean finished) {
        Client.finished = finished;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL xml = getClass().getResource("/LoginScreen.fxml");
        fxmlLoader.setLocation(xml);

        Registrator registrator = new Registrator(new Scanner(System.in));
        Parent root = fxmlLoader.load();

        LoginScreenController loginScreenController = fxmlLoader.getController();
        fxmlLoader.setController(loginScreenController);
        primaryStage.setScene(new Scene(root));

        loginScreenController.setChannel(registrator.register());
        loginScreenController.setListeners(new ArrayList<>(Arrays.asList(new
                InetSocketAddress(InetAddress.getByName("localhost"), 6666))));
        loginScreenController.setBase(new ArrayList<>());

        primaryStage.show();
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