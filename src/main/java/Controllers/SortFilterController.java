package Controllers;

import Main.Client;
import Models.Rule;
import Resource.ResourceDefault;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SortFilterController {
    private MainScreenController controller;
    private Option purpose;
    private int id;

    @FXML
    private ComboBox<String> columnBox;

    @FXML
    private ComboBox<String> optionBox;

    @FXML
    private TextField textField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Text columnText;

    @FXML
    private Text actionText;

    @FXML
    private Text parameterText;

    @FXML
    private Label headerLabel;

    @FXML
    private Text sortText;

    @FXML
    public void initialize() {
        Arrays.stream(Rule.Column.values()).forEach(x -> this.columnBox.getItems().add(Client.resourceFactory
                .getResources().getString(x.getName())));

        Arrays.stream(Rule.Order.values()).forEach(x -> this.optionBox.getItems().add(Client.resourceFactory
                .getResources().getString(x.getName())));

        this.sortText.textProperty().bind(Client.resourceFactory.getStringBinding("sort_text"));
        this.columnText.textProperty().bind(Client.resourceFactory.getStringBinding("column"));
        this.actionText.textProperty().bind(Client.resourceFactory.getStringBinding("order"));
        this.parameterText.textProperty().bind(Client.resourceFactory.getStringBinding("parameter"));
        this.headerLabel.textProperty().bind(Client.resourceFactory.getStringBinding("sorting_and_filtration"));
        this.okButton.textProperty().bind(Client.resourceFactory.getStringBinding("ok"));
        this.cancelButton.textProperty().bind(Client.resourceFactory.getStringBinding("cancel"));
    }

    @FXML
    public void confirm() {
        Rule rule = new Rule(Rule.Column.getEnum(Client.resourceFactory.getLocale(this.columnBox.getSelectionModel()
                .getSelectedItem(), new ResourceDefault())), Rule.Order.getEnum(Client.resourceFactory.getLocale(this.
                optionBox.getSelectionModel().getSelectedItem(), new ResourceDefault())), this.textField.getText());

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

