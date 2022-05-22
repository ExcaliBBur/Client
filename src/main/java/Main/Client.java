package Main;

import Controllers.LoginScreenController;
import Models.*;
import Interaction.Parser;
import Interaction.Receiver;
import Interaction.Sender;
import Realisation.*;
import Resource.ResourceHungarian;
import Resource.ResourcePortuguese;
import Resource.ResourceRussian;
import Resource.ResourceSpanish;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Main class
 */
public class Client extends Application {
    public static final ObservableResourceFactory resourceFactory = new ObservableResourceFactory();

    public static void main(String[] args) {
        Application.launch();
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

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        StorageListener listener = new StorageListener(sender.getChannel(), new LinkedList<>(), lock);
        listener.start();

        loginScreenController.setSender(sender);
        loginScreenController.setListener(listener);

        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
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

    public enum Languages {
        RUSSIAN("Русский") {
            @Override
            public ListResourceBundle getResources() {
                return new ResourceRussian();
            }
        },
        SPANISH("Español") {
            @Override
            public ListResourceBundle getResources() {
                return new ResourceSpanish();
            }
        },
        HUNGARIAN("Magyar") {
            @Override
            public ListResourceBundle getResources() {
                return new ResourceHungarian();
            }
        },
        PORTUGUESE("Português") {
            @Override
            public ListResourceBundle getResources() {
                return new ResourcePortuguese();
            }
        };

        private final String name;

        Languages(String name) {
            this.name = name;
        }

        public abstract ListResourceBundle getResources();

        public String getName() {
            return name;
        }

        public static Languages getEnum(String value) {
            for (Languages language : values()) {
                if (language.getName().equals(value)) return language;
            }
            return null;
        }
    }
}