package Controllers;

import Exceptions.InputException;
import Interaction.Parser;
import Models.City;
import Models.Controller;
import Models.User;
import Realisation.CityFormer;
import Realisation.CommandManager;
import Realisation.Executioner;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ExecuteScriptScreenController extends Controller {
    private CommandManager commandManager;
    private User user;

    @FXML
    private TextField filesPath;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    public void setUser(User user) {
        this.user = user;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @FXML
    private void confirm() {
        try {
            Path path = Paths.get(this.filesPath.getText());
            Scanner scanner = new Scanner(path);

            Executioner<City> executioner = new Executioner<>(user, new CityFormer(scanner), commandManager);

            while (scanner.hasNextLine()) {
                try {
                    this.getSender().sendResponse(Parser.parseTo(executioner.execute(scanner.nextLine())));
                } catch (InputException ignore) {

                }
            }

            this.okButton.getScene().getWindow().hide();
        } catch (IOException e) {
            alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cancel() {
        this.cancelButton.getScene().getWindow().hide();
    }
}
