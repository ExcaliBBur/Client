package Controllers;

import Exceptions.InputException;
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
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LoginScreenController {
    private StorageListener listener;
    private Sender sender;

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
        User user = new User(name.getText(), new HashPassword().hash(password.getText()));
        byte[] data = Parser.parseTo(new ClientDTO(new Command.CommandData(command,
                Arrays.asList(Serializer.serialize(user))), false,
                null));

        sender.sendResponse(data);

        ServerDTO<City> answer = this.blockAuth(listener);

        if (answer != null) {
            if (answer.isSuccess()) {
                this.changeScreen(user, listener, answer);
            } else {
                alert(new String(answer.getMessage()));
            }
        }
    }

    private void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setContentText(message);
        alert.showAndWait();
    }

    private ServerDTO<City> blockAuth(StorageListener listener) {
        ReentrantReadWriteLock.ReadLock readLock = listener.getLock().readLock();
        ServerDTO<City> serverDTO;
        long currentTime = System.currentTimeMillis();

        do {
            readLock.lock();
            serverDTO = listener.getNextAnswer();
            readLock.unlock();
        } while (serverDTO == null && (System.currentTimeMillis() - currentTime < 1000));

        if (serverDTO == null) {
            alert(new InputException.ServerUnavailableException().getMessage());
        }

        return serverDTO;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setListener(StorageListener listener) {
        this.listener = listener;
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
            controller.setSender(sender);
            controller.setListener(listener);

            controller.updateContents(answer.getCollection());

            listener.setController(controller);

            secondStage.show();
            secondStage.setOnCloseRequest(windowEvent -> System.exit(0));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
