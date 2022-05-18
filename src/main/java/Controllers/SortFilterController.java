package Controllers;

import Models.Rule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SortFilterController {
    private MainScreenController controller;
    private Option purpose;
    private int id;

    @FXML
    private ComboBox<Rule.Column> columnBox;

    @FXML
    private ComboBox<Rule.Order> optionBox;

    @FXML
    private TextField textField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void confirm() {
        Rule rule = new Rule(this.columnBox.getSelectionModel().getSelectedItem(), this.optionBox
                .getSelectionModel().getSelectedItem(), this.textField.getText());

        if (rule.getColumn() != null && rule.getOrder() != null) {
            switch (purpose) {
                case ADD: {
                    controller.addRuleContents(rule);
                    this.okButton.getScene().getWindow().hide();
                    break;
                }
                case EDIT: {
                    controller.editRuleContents(this.id, rule);
                    this.okButton.getScene().getWindow().hide();
                }
            }
        }
    }

    public void setPurpose(Option purpose) {
        this.purpose = purpose;
    }

    public void setController(MainScreenController controller) {
        this.controller = controller;
    }

    public void setID(int id) {
        this.id = id;
    }

    @FXML
    public void cancel() {
        this.cancelButton.getScene().getWindow().hide();
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

