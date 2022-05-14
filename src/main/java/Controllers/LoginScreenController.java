package Controllers;

import Interaction.Parser;
import Interaction.Sender;
import Models.Command;
import Models.User;
import Realisation.ClientDTO;
import Realisation.StorageListener;
import Utilities.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginScreenController {
    private DatagramSocket channel;
    private List<SocketAddress> listeners;

    @FXML
    //TODO Честно, не знаю пока, что там будет.
    private ComboBox<Button> languages;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    public void registration() {
        byte[] data = Parser.parseTo(new ClientDTO(new Command.CommandData("register",
                Arrays.asList(Serializer.serialize(new User(name.getText(), password.getText())))), true,
                null));
        this.getListeners().forEach(x -> Sender.sendResponse(this.getChannel(), data, x));
    }

    @FXML
    public void log() {
        byte[] data = Parser.parseTo(new ClientDTO(new Command.CommandData("login",
                Arrays.asList(Serializer.serialize(new User(name.getText(), password.getText())))), true,
                null));
        this.getListeners().forEach(x -> Sender.sendResponse(this.getChannel(), data, x));


    }

    public void finishInitialization() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new StorageListener());
    }

    public void setChannel(DatagramSocket channel) {
        this.channel = channel;
    }

    public DatagramSocket getChannel() {
        return channel;
    }

    public void addListeners(SocketAddress... listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
    }

    //TODO ДОБАВИТЬ СЛУШАТЕЛЕЙ (СЕРВЕР) В МЕТОД start() класса Client

    public void setListeners(List<SocketAddress> listeners) {
        this.listeners = listeners;
    }

    public List<SocketAddress> getListeners() {
        return listeners;
    }
}
