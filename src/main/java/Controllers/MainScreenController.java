package Controllers;

import Exceptions.InputException;
import Interaction.Parser;
import Interfaces.IFormer;
import Models.*;
import Realisation.ClientDTO;
import Realisation.StorageListener;
import Utilities.Serializer;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainScreenController extends StorageController<City> {
    private final ObservableList<Rule> ruleList = FXCollections.observableArrayList();
    private final ObservableList<City> sortedCityList = FXCollections.observableArrayList();
    private User user;

    @FXML
    private TableView<City> contentTable;

    @FXML
    private TableColumn<City, Integer> idColumn;

    @FXML
    private TableColumn<City, String> nameColumn;

    @FXML
    private TableColumn<City, City.Coordinates> coordinatesColumn;

    @FXML
    private TableColumn<City, Double> xColumn;

    @FXML
    private TableColumn<City, Float> yColumn;

    @FXML
    private TableColumn<City, String> creationDateColumn;

    @FXML
    private TableColumn<City, Integer> areaColumn;

    @FXML
    private TableColumn<City, Integer> populationColumn;

    @FXML
    private TableColumn<City, Integer> metersAboveSeaLevelColumn;

    @FXML
    private TableColumn<City, City.Climate> climateColumn;

    @FXML
    private TableColumn<City, City.Government> governmentColumn;

    @FXML
    private TableColumn<City, City.StandardOfLiving> standardOfLivingColumn;

    @FXML
    private TableColumn<City, City.Human> governorColumn;

    @FXML
    private TableColumn<City, String> humanNameColumn;

    @FXML
    private TableView<Rule> ruleTable;

    @FXML
    private TableColumn<Rule, Rule.Column> columnColumn;

    @FXML
    private TableColumn<Rule, Rule.Order> orderColumn;

    @FXML
    private TableColumn<Rule, String> parameterColumn;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField usernameTextBlock;

    @FXML
    private Button okButton;

    @FXML
    private RadioButton addRadioButton;

    @FXML
    private RadioButton editRadioButton;

    @FXML
    private RadioButton addIfMinRadioButton;

    @FXML
    private RadioButton removeGreaterRadioButton;

    @FXML
    private RadioButton removeLowerRadioButton;

    @FXML
    private ToggleGroup commandGroup;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField xInput;

    @FXML
    private TextField yInput;

    @FXML
    private TextField areaInput;

    @FXML
    private TextField populationInput;

    @FXML
    private TextField metersInput;

    @FXML
    private TextField climateInput;

    @FXML
    private TextField governmentInput;

    @FXML
    private TextField standardOfLivingInput;

    @FXML
    private TextField humanNameInput;

    public void setUser(User user) {
        this.user = user;
    }

    //TODO СТОИТ ЗАДУМАТЬСЯ НАД ТЕМ, ЧТОБЫ ОБЪЕДИНИТЬ ВСЕ СЕТТЕРЫ В КАКОЙ-НИБУДЬ МЕТОД, КОТОРЫЙ БЫ УСТАНАВЛИВАЛ ВСЕ ТРЕБУЕМЫЕ ЗНАЧЕНИЯ.

    @FXML
    public void initialize() {
        this.idColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        idColumn.setSortable(false);

        this.nameColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        nameColumn.setSortable(false);

        this.xColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getCoordinates().getFirstCoordinates()));
        xColumn.setSortable(false);

        this.yColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getCoordinates().getSecondCoordinates()));
        yColumn.setSortable(false);

        this.creationDateColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getCreationDate().toString()
                        .replace("T", " ")));
        creationDateColumn.setSortable(false);

        this.areaColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getArea()));
        areaColumn.setSortable(false);

        this.populationColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getPopulation()));
        populationColumn.setSortable(false);

        this.metersAboveSeaLevelColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getMeters()));
        metersAboveSeaLevelColumn.setSortable(false);

        this.climateColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getClimate()));
        climateColumn.setSortable(false);

        this.governmentColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getGovernment()));
        governmentColumn.setSortable(false);

        this.standardOfLivingColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getStandardOfLiving()));
        standardOfLivingColumn.setSortable(false);

        this.humanNameColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getGovernor().getHumanName()));
        humanNameColumn.setSortable(false);

        this.columnColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getColumn()));
        columnColumn.setSortable(false);

        this.orderColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getOrder()));
        this.orderColumn.setSortable(false);

        this.parameterColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getParameter()));
        this.parameterColumn.setSortable(false);

        contentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int id = contentTable.getSelectionModel().getSelectedIndex();
            if (id != -1) {
                this.nameInput.setText(this.getCollection().get(id).getName());
                this.xInput.setText(this.getCollection().get(id).getCoordinates().getFirstCoordinates().toString());
                this.yInput.setText(this.getCollection().get(id).getCoordinates().getSecondCoordinates().toString());
                this.areaInput.setText(this.getCollection().get(id).getArea().toString());
                this.populationInput.setText(this.getCollection().get(id).getPopulation().toString());
                this.metersInput.setText(Integer.toString(this.getCollection().get(id).getMeters()));
                try {
                    this.climateInput.setText(this.getCollection().get(id).getClimate().getName());
                } catch (NullPointerException e) {
                    this.climateInput.setText("");
                }
                try {
                    this.governmentInput.setText(this.getCollection().get(id).getGovernment().getName());
                } catch (NullPointerException e) {
                    this.governmentInput.setText("");
                }
                try {
                    this.standardOfLivingInput.setText(this.getCollection().get(id).getStandardOfLiving().getName());
                } catch (NullPointerException e) {
                    this.standardOfLivingInput.setText("");
                }
                this.humanNameInput.setText(this.getCollection().get(id).getGovernor().getHumanName());
            }
        });
    }

    public void updateContents(Collection<City> collection) {
        this.usernameTextBlock.setText(user.getName());
        this.getCollection().setAll(collection);

        this.fillCityTable();
    }

    private void fillCityTable() {
        Stream<City> stream = this.getCollection().stream();
        for (Rule rule : this.ruleList) {
            stream = rule.transform(stream);
        }

        this.sortedCityList.setAll(stream.collect(Collectors.toList()));
        contentTable.getItems().setAll(sortedCityList);
    }

    public void addRuleContents(Rule rule) {
        this.ruleList.add(rule);
        this.ruleTable.getItems().setAll(ruleList);

        this.fillCityTable();
    }

    public void editRuleContents(int id, Rule edited) {
        this.ruleList.set(id, edited);
        this.ruleTable.getItems().setAll(this.ruleList);

        this.fillCityTable();
    }

    private byte[] transformData(String keyWord, List<String> arguments) {
        return Parser.parseTo(new ClientDTO(new Command.CommandData(keyWord, arguments), false, this.user));
    }

    @FXML
    private void remove() {
        try {
            int id = contentTable.getSelectionModel().getSelectedIndex();

            if (id >= 0) {
                this.contentTable.getSelectionModel().clearSelection();
                byte[] data = this.transformData("remove_by_id", Arrays
                        .asList(Serializer.serialize(this.getCollection().get(id).getId())));

                this.getSender().sendResponse(data);
                ServerDTO<City> serverDTO = this.blockGetAnswer();

                if (serverDTO != null && !serverDTO.isSuccess()) {
                    alert(new String(serverDTO.getMessage()), Alert.AlertType.ERROR);
                }
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    @FXML
    private void clear() {
        this.contentTable.getSelectionModel().clearSelection();
        byte[] data = this.transformData("clear", null);

        this.getSender().sendResponse(data);
        ServerDTO<City> serverDTO = this.blockGetAnswer();

        if (serverDTO != null && !serverDTO.isSuccess()) {
            alert(new String(serverDTO.getMessage()), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void logout() throws IOException {
        logoutButton.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL xml = getClass().getResource("/LoginScreen.fxml");
        fxmlLoader.setLocation(xml);
        Stage stage = new Stage();
        Parent root = fxmlLoader.load();

        LoginScreenController loginScreenController = fxmlLoader.getController();
        fxmlLoader.setController(loginScreenController);
        stage.setScene(new Scene(root));

        loginScreenController.setSender(this.getSender());
        loginScreenController.setListener(this.getListener());

        stage.show();
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
    }

    @FXML
    private void doSomething() {
        RadioButton rb = (RadioButton) commandGroup.getSelectedToggle();

        if (rb != null) {
            IFormer<City> iFormer = () -> {
                try {
                    City object = new City();
                    object.setInputName(this.nameInput.getText());
                    object.setCoordinates(new City.Coordinates());
                    object.getCoordinates().setInputFirstCoordinates(this.xInput.getText());
                    object.getCoordinates().setInputSecondCoordinates(this.yInput.getText());
                    object.setInputArea(this.areaInput.getText());
                    object.setInputPopulation(this.populationInput.getText());
                    object.setInputMeters(this.metersInput.getText());
                    object.setInputClimate(this.climateInput.getText());
                    object.setInputGovernment(this.governmentInput.getText());
                    object.setInputStandardOfLiving(this.standardOfLivingInput.getText());
                    object.setGovernor(new City.Human());
                    object.getGovernor().setInputHumanName(this.humanNameInput.getText());
                    return object;
                } catch (InputException e) {
                    alert(e.getMessage(), Alert.AlertType.ERROR);
                }
                return null;
            };

            try {
                City city = iFormer.formObj();

                if (city != null) {
                    byte[] data;
                    if (rb.getText().equals("EDIT")) {
                        int id = this.contentTable.getSelectionModel().getSelectedIndex();
                        data = this.transformData("update", new ArrayList<>(Arrays.asList(Integer
                                .toString(this.getCollection().get(id).getId()), Serializer.serialize(city))));
                    } else {
                        data = this.transformData(rb.getText().toLowerCase().replace(" ", "_"),
                                Arrays.asList(Serializer.serialize(city)));
                    }

                    this.getSender().sendResponse(data);
                    ServerDTO<City> serverDTO = this.blockGetAnswer();
                    if (serverDTO != null && !serverDTO.isSuccess()) {
                        alert(new String(serverDTO.getMessage()), Alert.AlertType.ERROR);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
        }
    }

    @FXML
    private void addRule() {
        try {
            FXMLLoader preset = new FXMLLoader();
            URL xml = getClass().getResource("/SortFilter.fxml");
            preset.setLocation(xml);

            Parent root = preset.load();
            Stage additionalStage = new Stage();
            additionalStage.setScene(new Scene(root));
            additionalStage.initModality(Modality.APPLICATION_MODAL);

            SortFilterController sortFilterController = preset.getController();
            sortFilterController.setPurpose(SortFilterController.Option.ADD);
            sortFilterController.setController(this);

            additionalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editRule() {
        try {
            FXMLLoader preset = new FXMLLoader();
            URL xml = getClass().getResource("/SortFilter.fxml");
            preset.setLocation(xml);

            Parent root = preset.load();
            Stage additionalStage = new Stage();
            additionalStage.setScene(new Scene(root));
            additionalStage.initModality(Modality.APPLICATION_MODAL);

            SortFilterController sortFilterController = preset.getController();
            sortFilterController.setController(this);
            sortFilterController.setPurpose(SortFilterController.Option.EDIT);

            int id = this.ruleTable.getSelectionModel().getSelectedIndex();
            sortFilterController.setID(id);

            additionalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    @FXML
    private void removeRule() {
        try {
            this.ruleList.remove(this.ruleTable.getSelectionModel().getSelectedIndex());
            this.ruleTable.getItems().setAll(ruleList);

            fillCityTable();
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }
}
