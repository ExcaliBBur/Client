package Controllers;

import Interaction.Parser;
import Interaction.Sender;
import Models.*;
import Realisation.ClientDTO;
import Realisation.HashPassword;
import Realisation.StorageListener;
import Utilities.Serializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    public void registration() {
        this.action("register");
    }

    @FXML
    public void log() {
        this.action("login");
    }
    //TODO ДОБАВИТЬ ПРОВЕРКУ НЕПУСТОГО ЛОГИНА

    public void action(String command) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        User user = new User(name.getText(), new HashPassword().hash(password.getText()));
        byte[] data = Parser.parseTo(new ClientDTO(new Command.CommandData(command,
                Arrays.asList(Serializer.serialize(user))), false,
                null));

        StorageListener listener = new StorageListener(channel, new LinkedList<>(), lock);
        listener.start();
        this.getListeners().forEach(x -> Sender.sendResponse(this.getChannel(), data, x));

        ServerDTO<City> answer = this.blockAuth(listener, lock);

        if (answer.isSuccess()) {
            this.changeScreen(user, listener, answer);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Dick!");
            alert.showAndWait();

            listener.interrupt();
        }
    }

    private ServerDTO<City> blockAuth(StorageListener listener, ReentrantReadWriteLock lock) {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ServerDTO<City> serverDTO;
        do {
            readLock.lock();
            serverDTO = listener.getNextAnswer();
            readLock.unlock();
        } while (serverDTO == null);

        return serverDTO;
    }

    public DatagramSocket getChannel() {
        return channel;
    }

    public List<SocketAddress> getListeners() {
        return listeners;
    }

    private void changeScreen(User user, StorageListener listener, ServerDTO<City> answer) {
        try {
            loginButton.getScene().getWindow().hide();
            FXMLLoader preset = new FXMLLoader();
            URL xml = getClass().getResource("/MainScreen.fxml");
            preset.setLocation(xml);

            Parent root = preset.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));

            MainScreenController controller = preset.getController();
            controller.setUser(user);
            controller.updateContents(answer.getCollection());
            System.out.println(controller.getCollection().size());

            listener.setController(controller);

            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setListeners(List<SocketAddress> listeners) {
        this.listeners = listeners;
    }

    public void setChannel(DatagramSocket channel) {
        this.channel = channel;
    }
}
