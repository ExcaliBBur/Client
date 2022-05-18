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
        Parent root = fxmlLoader.load();

        LoginScreenController loginScreenController = fxmlLoader.getController();
        fxmlLoader.setController(loginScreenController);
        primaryStage.setScene(new Scene(root));

        Sender sender = new Sender(new Registrator().register(), new InetSocketAddress(InetAddress
                .getByName("localhost"), 6666));

        loginScreenController.setSender(sender);

        primaryStage.show();
    }

    public static class Registrator {

        public DatagramSocket register() {
            boolean isCorrect;
            int tmp = 0;
            DatagramSocket datagramSocket = null;
            System.out.println("Enter the port.");
            do {
                isCorrect = false;
                try {
                    for (int i = 1; i < 65000; i++) {
                        datagramSocket = new DatagramSocket(i);
                        isCorrect = true;
                        tmp = i;
                    }
                } catch (SocketException ignored) {

                }
            } while (!isCorrect);
            System.out.println("Сервер стартует на порте " + tmp);
            return datagramSocket;
        }
    }
}