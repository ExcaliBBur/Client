package Controllers;

import Models.Command;
import Models.Rule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class SortFilterController {
    private Option purpose;
    private List<Command> rules;

    @FXML
    private ChoiceBox<Rule.Order> optionBox;

    @FXML
    private ComboBox<Rule.Column> columnBox;

    @FXML
    private TextField textField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void confirm() {
        switch (purpose) {
            case ADD: {

            }
            case EDIT: {

            }
        }
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        this.columnBox.getItems().addAll(Rule.Column.values());
        this.optionBox.getItems().addAll(Rule.Order.values());
    }

    public enum Option {
        EDIT("EDIT"),
        ADD("ADD");

        private final String name;

        Option(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}

