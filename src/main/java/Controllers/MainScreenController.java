package Controllers;

import Exceptions.InputException;
import Interaction.Parser;
import Interfaces.IFormer;
import Main.Client;
import Models.*;
import Realisation.ClientDTO;
import Resource.ResourceDefault;
import Utilities.Serializer;
import javafx.animation.ScaleTransition;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import Realisation.CommandManager;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainScreenController extends StorageController<City> {
    private final ObservableList<Rule> ruleList = FXCollections.observableArrayList();
    private CommandManager commandManager;
    private User user;
    private final HashMap<Shape, Integer> shapeMap = new HashMap<>();
    private boolean flag = true;
    private final HashMap<String, Color> colorHashMap = new HashMap<>();
    private final List<Integer> idList = new ArrayList<>();
    private final Canvas canvas = new Canvas(1280, 980);

    @FXML
    private AnchorPane secondMainScreen;

    @FXML
    private TabPane tabPane;

    @FXML
    private AnchorPane firstMainScreen;

    @FXML
    private TableView<City> objectTable;


    @FXML
    private TableView<City> contentTable;

    @FXML
    private TableColumn<City, String> idColumn;

    @FXML
    private TableColumn<City, String> nameColumn;

    @FXML
    private TableColumn<City, City.Coordinates> coordinatesColumn;

    @FXML
    private TableColumn<City, String> xColumn;

    @FXML
    private TableColumn<City, String> yColumn;

    @FXML
    private TableColumn<City, String> creationDateColumn;

    @FXML
    private TableColumn<City, String> areaColumn;

    @FXML
    private TableColumn<City, String> populationColumn;

    @FXML
    private TableColumn<City, String> metersAboveSeaLevelColumn;

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
    private TableColumn<City, String> usernameColumn;

    @FXML
    private TableView<Rule> ruleTable;

    @FXML
    private TableColumn<Rule, String> columnColumn;

    @FXML
    private TableColumn<Rule, String> orderColumn;

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

    @FXML
    private Text userText;

    @FXML
    private Button executeScriptButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button removeButton;

    @FXML
    private TableColumn<Rule, Rule> ruleHeader;

    @FXML
    private Button editButton;

    @FXML
    private Button addButton;

    @FXML
    private Button removeRuleButton;

    @FXML
    private TableColumn<City, String> idColumnSecond;

    @FXML
    private TableColumn<City, String> nameColumnSecond;

    @FXML
    private TableColumn<City, City.Coordinates> coordinatesColumnSecond;

    @FXML
    private TableColumn<City, String> xColumnSecond;

    @FXML
    private TableColumn<City, String> yColumnSecond;

    @FXML
    private TableColumn<City, String> creationDateColumnSecond;

    @FXML
    private TableColumn<City, String> areaColumnSecond;

    @FXML
    private TableColumn<City, String> populationColumnSecond;

    @FXML
    private TableColumn<City, String> metersAboveSeaLevelColumnSecond;

    @FXML
    private TableColumn<City, City.Climate> climateColumnSecond;

    @FXML
    private TableColumn<City, City.Government> governmentColumnSecond;

    @FXML
    private TableColumn<City, City.StandardOfLiving> standardOfLivingColumnSecond;

    @FXML
    private TableColumn<City, City.Human> governorColumnSecond;

    @FXML
    private TableColumn<City, String> humanNameColumnSecond;

    @FXML
    private TableColumn<City, String> usernameColumnSecond;

    @FXML
    private Tab mainTab;

    @FXML
    private Tab mapTab;

    public void setUser(User user) {
        this.user = user;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @FXML
    public void initialize() {
        firstMainScreen.widthProperty().addListener((observableValue, number, t1) -> autoScaling());
        firstMainScreen.heightProperty().addListener((observableValue, number, t1) -> autoScaling());
        secondMainScreen.widthProperty().addListener((observableValue, number, t1) -> autoScalingSecond());
        this.idColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getId())));
        idColumn.setSortable(false);

        this.nameColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        nameColumn.setSortable(false);

        this.xColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getCoordinates().getFirstCoordinates())));
        xColumn.setSortable(false);

        this.yColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getCoordinates().getSecondCoordinates())));
        yColumn.setSortable(false);

        this.creationDateColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .withLocale(Client.resourceFactory.getLanguage().getLocale()).format(cellData.getValue()
                                .getCreationDate().atZone(ZoneId.systemDefault()))));
        creationDateColumn.setSortable(false);

        this.areaColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getArea())));
        areaColumn.setSortable(false);

        this.populationColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getPopulation())));
        populationColumn.setSortable(false);

        this.metersAboveSeaLevelColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getMeters())));
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

        this.usernameColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getUsername()));
        usernameColumn.setSortable(false);

        this.columnColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(Client.resourceFactory.getResources().getString(cellData.getValue()
                        .getColumn().getName())));
        columnColumn.setSortable(false);

        this.orderColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(Client.resourceFactory.getResources().getString(cellData.getValue()
                        .getOrder().getName())));
        this.orderColumn.setSortable(false);

        this.parameterColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getParameter()));
        this.parameterColumn.setSortable(false);
        this.idColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage()
                        .getLocale()).format(cellData.getValue().getId())));
        idColumnSecond.setSortable(false);

        this.nameColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        nameColumnSecond.setSortable(false);

        this.xColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getCoordinates().getFirstCoordinates())));
        xColumnSecond.setSortable(false);

        this.yColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getCoordinates().getSecondCoordinates())));
        yColumnSecond.setSortable(false);

        this.creationDateColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .withLocale(Client.resourceFactory.getLanguage().getLocale()).format(cellData.getValue()
                                .getCreationDate().atZone(ZoneId.systemDefault()))));
        creationDateColumnSecond.setSortable(false);

        this.areaColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getArea())));
        areaColumnSecond.setSortable(false);

        this.populationColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getPopulation())));
        populationColumnSecond.setSortable(false);

        this.metersAboveSeaLevelColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(NumberFormat.getInstance(Client.resourceFactory.getLanguage().getLocale())
                        .format(cellData.getValue().getMeters())));
        metersAboveSeaLevelColumnSecond.setSortable(false);

        this.climateColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getClimate()));
        climateColumnSecond.setSortable(false);

        this.governmentColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getGovernment()));
        governmentColumnSecond.setSortable(false);

        this.standardOfLivingColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getStandardOfLiving()));
        standardOfLivingColumnSecond.setSortable(false);

        this.humanNameColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getGovernor().getHumanName()));
        humanNameColumnSecond.setSortable(false);

        this.usernameColumnSecond.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getUsername()));
        usernameColumnSecond.setSortable(false);

        contentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int id = contentTable.getSelectionModel().getSelectedIndex();
            if (id != -1) {
                this.nameInput.setText(this.contentTable.getItems().get(id).getName());
                this.xInput.setText(this.contentTable.getItems().get(id).getCoordinates().getFirstCoordinates()
                        .toString());
                this.yInput.setText(this.contentTable.getItems().get(id).getCoordinates().getSecondCoordinates()
                        .toString());
                this.areaInput.setText(this.contentTable.getItems().get(id).getArea().toString());
                this.populationInput.setText(this.contentTable.getItems().get(id).getPopulation().toString());
                this.metersInput.setText(Integer.toString(this.contentTable.getItems().get(id).getMeters()));
                try {
                    this.climateInput.setText(this.contentTable.getItems().get(id).getClimate().getName());
                } catch (NullPointerException e) {
                    this.climateInput.setText("");
                }
                try {
                    this.governmentInput.setText(this.contentTable.getItems().get(id).getGovernment().getName());
                } catch (NullPointerException e) {
                    this.governmentInput.setText("");
                }
                try {
                    this.standardOfLivingInput.setText(this.contentTable.getItems().get(id).getStandardOfLiving()
                            .getName());
                } catch (NullPointerException e) {
                    this.standardOfLivingInput.setText("");
                }
                this.humanNameInput.setText(this.contentTable.getItems().get(id).getGovernor().getHumanName());
            }
        });

        this.userText.textProperty().bind(Client.resourceFactory.getStringBinding("username"));
        this.nameInput.promptTextProperty().bind(Client.resourceFactory.getStringBinding("name"));
        this.xInput.promptTextProperty().bind(Client.resourceFactory.getStringBinding("coordinate_x"));
        this.yInput.promptTextProperty().bind(Client.resourceFactory.getStringBinding("coordinate_y"));
        this.areaInput.promptTextProperty().bind(Client.resourceFactory.getStringBinding("area"));
        this.populationInput.promptTextProperty().bind(Client.resourceFactory.getStringBinding("population"));
        this.metersInput.promptTextProperty().bind(Client.resourceFactory.getStringBinding("meters"));
        this.climateInput.promptTextProperty().bind(Client.resourceFactory.getStringBinding("climate"));
        this.governmentInput.promptTextProperty().bind(Client.resourceFactory.getStringBinding("government"));
        this.standardOfLivingInput.promptTextProperty().bind(Client.resourceFactory.getStringBinding("standard"));
        this.humanNameInput.promptTextProperty().bind(Client.resourceFactory.getStringBinding("human"));
        this.okButton.textProperty().bind(Client.resourceFactory.getStringBinding("ok"));
        this.addRadioButton.textProperty().bind(Client.resourceFactory.getStringBinding("add"));
        this.editRadioButton.textProperty().bind(Client.resourceFactory.getStringBinding("edit"));
        this.addIfMinRadioButton.textProperty().bind(Client.resourceFactory.getStringBinding("add_if_min"));
        this.removeGreaterRadioButton.textProperty().bind(Client.resourceFactory
                .getStringBinding("remove_greater"));
        this.removeLowerRadioButton.textProperty().bind(Client.resourceFactory.getStringBinding("remove_lower"));
        this.idColumn.textProperty().bind(Client.resourceFactory.getStringBinding("id"));
        this.nameColumn.textProperty().bind(Client.resourceFactory.getStringBinding("name"));
        this.coordinatesColumn.textProperty().bind(Client.resourceFactory.getStringBinding("coordinates"));
        this.xColumn.textProperty().bind(Client.resourceFactory.getStringBinding("x"));
        this.yColumn.textProperty().bind(Client.resourceFactory.getStringBinding("y"));
        this.creationDateColumn.textProperty().bind(Client.resourceFactory.getStringBinding("creation_date"));
        this.areaColumn.textProperty().bind(Client.resourceFactory.getStringBinding("area"));
        this.populationColumn.textProperty().bind(Client.resourceFactory.getStringBinding("population"));
        this.metersAboveSeaLevelColumn.textProperty().bind(Client.resourceFactory.getStringBinding("meters"));
        this.climateColumn.textProperty().bind(Client.resourceFactory.getStringBinding("climate"));
        this.governmentColumn.textProperty().bind(Client.resourceFactory.getStringBinding("government"));
        this.standardOfLivingColumn.textProperty().bind(Client.resourceFactory.getStringBinding("standard"));
        this.governorColumn.textProperty().bind(Client.resourceFactory.getStringBinding("governor"));
        this.humanNameColumn.textProperty().bind(Client.resourceFactory.getStringBinding("human"));
        this.nameColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("name"));
        this.logoutButton.textProperty().bind(Client.resourceFactory.getStringBinding("logout"));
        this.executeScriptButton.textProperty().bind(Client.resourceFactory
                .getStringBinding("execute_script"));
        this.clearButton.textProperty().bind(Client.resourceFactory.getStringBinding("clear"));
        this.removeButton.textProperty().bind(Client.resourceFactory.getStringBinding("remove"));
        this.ruleHeader.textProperty().bind(Client.resourceFactory.getStringBinding("sorting_and_filtration"));
        this.columnColumn.textProperty().bind(Client.resourceFactory.getStringBinding("column"));
        this.orderColumn.textProperty().bind(Client.resourceFactory.getStringBinding("order"));
        this.parameterColumn.textProperty().bind(Client.resourceFactory.getStringBinding("parameter"));
        this.addButton.textProperty().bind(Client.resourceFactory.getStringBinding("add"));
        this.editButton.textProperty().bind(Client.resourceFactory.getStringBinding("edit"));
        this.removeRuleButton.textProperty().bind(Client.resourceFactory.getStringBinding("remove_rule"));
        this.usernameColumn.textProperty().bind(Client.resourceFactory.getStringBinding("username"));
        this.idColumn.textProperty().bind(Client.resourceFactory.getStringBinding("id"));
        this.coordinatesColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("coordinates"));
        this.xColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("x"));
        this.yColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("y"));
        this.creationDateColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("creation_date"));
        this.areaColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("area"));
        this.populationColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("population"));
        this.metersAboveSeaLevelColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("meters"));
        this.climateColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("climate"));
        this.governmentColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("government"));
        this.standardOfLivingColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("standard"));
        this.governorColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("governor"));
        this.humanNameColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("human"));
        this.usernameColumnSecond.textProperty().bind(Client.resourceFactory.getStringBinding("username"));
        this.mainTab.textProperty().bind(Client.resourceFactory.getStringBinding("main"));
        this.mapTab.textProperty().bind(Client.resourceFactory.getStringBinding("map"));
    }

    public void updateContents(Collection<City> collection) {
        this.usernameTextBlock.setText(user.getName());
        this.getCollection().setAll(collection);
        this.fillCityTable();

        Platform.runLater(this::fillMap);
    }

    private void fillCityTable() {
        Stream<City> stream = this.getCollection().stream();
        for (Rule rule : this.ruleList) {
            stream = rule.transform(stream);
        }

        contentTable.getItems().setAll(stream.collect(Collectors.toList()));
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
        return Parser.parseTo(new ClientDTO(new Command.CommandData(keyWord, arguments), Client.resourceFactory
                .getLanguage(), true, this.user));
    }

    @FXML
    private void remove() {
        if (!this.isWaiting()) {
            new Thread(() -> {
                this.setWaiting(true);
                try {
                    int id = contentTable.getSelectionModel().getSelectedIndex();

                    if (id >= 0) {
                        this.contentTable.getSelectionModel().clearSelection();
                        byte[] data = this.transformData("remove_by_id", Arrays
                                .asList(Serializer.serialize(this.contentTable.getItems().get(id).getId())));

                        this.getSender().sendResponse(data);
                        ServerDTO<City> serverDTO = this.blockGetAnswer();

                        if (serverDTO != null && !serverDTO.isSuccess()) {
                            alert(new String(serverDTO.getMessage(), StandardCharsets.UTF_8), Alert.AlertType.ERROR);
                        }
                    } else {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {

                } finally {
                    this.setWaiting(false);
                }
            }).start();
        }
    }

    @FXML
    private void executeScript() {
        try {
            FXMLLoader preset = new FXMLLoader();
            URL xml = getClass().getResource("/ExecuteScriptScreen.fxml");
            preset.setLocation(xml);

            Parent root = preset.load();
            Stage additionalStage = new Stage();
            additionalStage.setScene(new Scene(root));
            additionalStage.initModality(Modality.APPLICATION_MODAL);

            ExecuteScriptScreenController controller = preset.getController();
            controller.setCommandManager(commandManager);
            controller.setUser(user);
            controller.setSender(this.getSender());

            additionalStage.show();
            additionalStage.setResizable(false);
            additionalStage.titleProperty().bind(Client.resourceFactory.getStringBinding("execute_script"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clear() {
        if (!this.isWaiting()) {
            new Thread(() -> {
                this.setWaiting(true);
                this.contentTable.getSelectionModel().clearSelection();
                byte[] data = this.transformData("clear", null);

                this.getSender().sendResponse(data);
                ServerDTO<City> serverDTO = this.blockGetAnswer();

                if (serverDTO != null && !serverDTO.isSuccess()) {
                    alert(new String(serverDTO.getMessage(), StandardCharsets.UTF_8), Alert.AlertType.ERROR);
                }
                this.setWaiting(false);
            }).start();
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
        stage.titleProperty().bind(Client.resourceFactory.getStringBinding("start"));
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
                    String defaultName = Client.resourceFactory.getLocale(rb.getText(), new ResourceDefault());
                    byte[] data;
                    if (defaultName.equals("edit")) {
                        int id = this.contentTable.getSelectionModel().getSelectedIndex();
                        data = this.transformData("update", new ArrayList<>(Arrays.asList(Integer
                                .toString(this.getCollection().get(id).getId()), Serializer.serialize(city))));
                    } else {
                        data = this.transformData(defaultName, Arrays.asList(Serializer.serialize(city)));
                    }

                            this.getSender().sendResponse(data);
                            ServerDTO<City> serverDTO = this.blockGetAnswer();
                            if (serverDTO != null && !serverDTO.isSuccess()) {
                                alert(new String(serverDTO.getMessage(), StandardCharsets.UTF_8),
                                        Alert.AlertType.ERROR);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    } finally {
                        this.setWaiting(false);
                    }
                }
            }).start();
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
            additionalStage.setResizable(false);
            additionalStage.titleProperty().bind(Client.resourceFactory.getStringBinding("sorting_and_filtration"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editRule() {
        try {
            int id = this.ruleTable.getSelectionModel().getSelectedIndex();

            if (id != -1) {

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

                sortFilterController.setID(id);

                additionalStage.show();
                additionalStage.setResizable(false);
                additionalStage.titleProperty().bind(Client.resourceFactory
                        .getStringBinding("sorting_and_filtration"));
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    public void fillMap() {
        boolean contains = false;
        Set<Shape> keys = shapeMap.keySet();
        for (Object key : keys) {
            secondMainScreen.getChildren().remove(key);
        }

        shapeMap.clear();
        if (flag) {
            for (City city : this.getCollection()) {
                if (colorHashMap.get(city.getUsername()) == null) {
                    Color c = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255),
                            (int) (Math.random() * 255));
                    while (colorHashMap.containsValue(c)) {
                        c = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255),
                                (int) (Math.random() * 255));
                    }
                    colorHashMap.put(city.getUsername(), c);
                }
            }
        }
        List<City> list = this.getCollection().stream().sorted(Comparator.comparingInt(City::getArea).reversed())
                .collect(Collectors.toList());
        for (City city : list) {
            Shape circle = new Circle(((float) city.getArea() / 50) * canvas.getScaleX(), colorHashMap
                    .get(city.getUsername()));
            circle.setLayoutX(secondMainScreen.widthProperty().subtract(0).getValue() / 2
                    + city.getCoordinates().getFirstCoordinates() * canvas.getScaleX());
            circle.setLayoutY(secondMainScreen.heightProperty().subtract(0).getValue() / 2 +
                    city.getCoordinates().getSecondCoordinates() * canvas.getScaleX());
            shapeMap.put(circle, city.getId());
            if (!idList.contains(city.getId())) {
                idList.add(city.getId());
                contains = true;
            }
            circle.setStroke(Color.BLACK);
            secondMainScreen.setOnScroll(this::mouseScroll);
            circle.setOnMouseClicked(this::mouseClicked);
            circle.setOnMouseEntered(this::mouseEntered);
            if (circle.getLayoutY() - (float) city.getArea() / 50 * canvas.getScaleX() - 82 > 0) {
                secondMainScreen.getChildren().add(circle);
            }
            if (flag && contains) {
                contains = false;
                ScaleTransition circleAnimation = new ScaleTransition(Duration.millis(5000), circle);
                circleAnimation.setFromX(0);
                circleAnimation.setToX(1);
                circleAnimation.setFromY(0);
                circleAnimation.setToY(1);
                circleAnimation.play();
            }
        }
    }

    private void mouseClicked(MouseEvent event) {
        int count = 0;
        Shape shape = (Shape) event.getSource();
        int id = shapeMap.get(shape);
        for (City city : this.getCollection()) {
            count++;
            if (city.getId() == id) {
                tabPane.getSelectionModel().select(0);
                contentTable.getSelectionModel().select(count - 1, idColumn);
            }
        }
    }

    private void mouseEntered(MouseEvent event) {
        Shape shape = (Shape) event.getSource();
        int id = shapeMap.get(shape);
        for (City city : this.getCollection()) {
            if (city.getId() == id) objectTable.getItems().setAll(city);
        }
    }

    private void mouseScroll(ScrollEvent event) {
        double k = (event.getDeltaY() < 0 ? 0.9 : 1.1);
        double zr = canvas.getScaleX() * k;
        canvas.setScaleX(zr);
        canvas.setScaleY(zr);
        flag = false;
        fillMap();
        flag = true;
    }

    public void autoScaling() {
        logoutButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(15.26136));
        logoutButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(28.91176));

        okButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(13.43));
        okButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(19.66));

        clearButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(25.82692));
        clearButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(32.76667));

        executeScriptButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(12.79048));
        executeScriptButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(32.76667));

        addRadioButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(8.13529));
        addRadioButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(57.82353));

        editRadioButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(8.13529));
        editRadioButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(57.82353));

        addIfMinRadioButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(8.13529));
        addIfMinRadioButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(57.82353));

        removeGreaterRadioButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(8.13529));
        removeGreaterRadioButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(57.82353));

        removeLowerRadioButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(8.13529));
        removeLowerRadioButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(57.82353));

        removeButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(19.75714));
        removeButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(32.76667));

        addButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(16.37805));
        addButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(26.56757));

        editButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(16.37805));
        editButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(26.56757));

        removeRuleButton.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(10.9187));
        removeRuleButton.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(26.56757));

        contentTable.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(1.26698));
        contentTable.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(1.6661));

        ruleTable.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(4.86594));
        ruleTable.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(4.915));

        nameInput.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(7.67429));
        nameInput.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(98.3));

        xInput.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(7.67429));
        xInput.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(98.3));

        yInput.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(7.67429));
        yInput.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(98.3));

        areaInput.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(7.67429));
        areaInput.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(98.3));

        populationInput.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(7.67429));
        populationInput.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(98.3));

        metersInput.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(7.67429));
        metersInput.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(98.3));

        climateInput.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(7.67429));
        climateInput.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(98.3));

        governmentInput.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(7.67429));
        governmentInput.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(98.3));

        standardOfLivingInput.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(7.67429));
        standardOfLivingInput.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(98.3));

        humanNameInput.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(7.67429));
        humanNameInput.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(98.3));

        usernameTextBlock.prefWidthProperty().bind(firstMainScreen.widthProperty().divide(8.66452));
        usernameTextBlock.prefHeightProperty().bind(firstMainScreen.heightProperty().divide(32.76667));

        idColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(30.285714));
        nameColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(15.8209));
        xColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(17.0967));
        yColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(18.59649));
        creationDateColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(11.2766));
        areaColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(23.04348));
        populationColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(14.72));
        metersAboveSeaLevelColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(7.6259));
        climateColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(14.52055));
        governmentColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(10.70707));
        standardOfLivingColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(8.61789));
        humanNameColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(10.6));
        usernameColumn.prefWidthProperty().bind(contentTable.widthProperty().divide(13.8));
        columnColumn.prefWidthProperty().bind(ruleTable.widthProperty().divide(2.93617));
        orderColumn.prefWidthProperty().bind(ruleTable.widthProperty().divide(3.10112));
        parameterColumn.prefWidthProperty().bind(ruleTable.widthProperty().divide(3));

        DoubleBinding dbX = firstMainScreen.widthProperty().subtract(0);
        DoubleBinding dbY = firstMainScreen.heightProperty().subtract(0);

        dbX.addListener((ov, t, t1) -> {
            logoutButton.setLayoutX(dbX.getValue() / 1.114);
            okButton.setLayoutX(dbX.getValue() / 14.75824);
            clearButton.setLayoutX(dbX.getValue() / 2.66998);
            executeScriptButton.setLayoutX(dbX.getValue() / 4.71228);
            removeButton.setLayoutX(dbX.getValue() / 2.1873);
            addButton.setLayoutX(dbX.getValue() / 2.36861);
            editButton.setLayoutX(dbX.getValue() / 1.59123);
            removeRuleButton.setLayoutX(dbX.getValue() / 1.96345);

            nameInput.setLayoutX(dbX.getValue() / 27.97917);
            xInput.setLayoutX(dbX.getValue() / 27.97917);
            yInput.setLayoutX(dbX.getValue() / 27.97917);
            areaInput.setLayoutX(dbX.getValue() / 27.97917);
            populationInput.setLayoutX(dbX.getValue() / 27.97917);
            metersInput.setLayoutX(dbX.getValue() / 27.97917);
            climateInput.setLayoutX(dbX.getValue() / 27.97917);
            governmentInput.setLayoutX(dbX.getValue() / 27.97917);
            standardOfLivingInput.setLayoutX(dbX.getValue() / 27.97917);
            humanNameInput.setLayoutX(dbX.getValue() / 27.97917);

            addRadioButton.setLayoutX(dbX.getValue() / 21.31746);
            editRadioButton.setLayoutX(dbX.getValue() / 21.31746);
            addIfMinRadioButton.setLayoutX(dbX.getValue() / 21.31746);
            removeGreaterRadioButton.setLayoutX(dbX.getValue() / 21.31746);
            removeLowerRadioButton.setLayoutX(dbX.getValue() / 21.31746);

            contentTable.setLayoutX(dbX.getValue() / 5.32937);
            userText.setLayoutX(dbX.getValue() / 23.5614);
            usernameTextBlock.setLayoutX(dbX.getValue() / 21.31746);
            ruleTable.setLayoutX(dbX.getValue() / 2.20888);
        });

        dbY.addListener((ov, t, t1) -> {
            logoutButton.setLayoutY(dbY.getValue() / 29.78);
            okButton.setLayoutY(dbY.getValue() / 1.73982);
            clearButton.setLayoutY(dbY.getValue() / 26.56757);
            executeScriptButton.setLayoutY(dbY.getValue() / 26.56767);
            removeButton.setLayoutY(dbY.getValue() / 26.56767);
            addButton.setLayoutY(dbY.getValue() / 1.06501);
            editButton.setLayoutY(dbY.getValue() / 1.06501);
            removeRuleButton.setLayoutY(dbY.getValue() / 1.06501);

            nameInput.setLayoutY(dbY.getValue() / 6.46711);
            xInput.setLayoutY(dbY.getValue() / 5.09326);
            yInput.setLayoutY(dbY.getValue() / 4.20085);
            areaInput.setLayoutY(dbY.getValue() / 3.57455);
            populationInput.setLayoutY(dbY.getValue() / 3.11076);
            metersInput.setLayoutY(dbY.getValue() / 2.7535);
            climateInput.setLayoutY(dbY.getValue() / 2.46985);
            governmentInput.setLayoutY(dbY.getValue() / 2.23918);
            standardOfLivingInput.setLayoutY(dbY.getValue() / 2.047916);
            humanNameInput.setLayoutY(dbY.getValue() / 1.88676);

            addRadioButton.setLayoutY(dbY.getValue() / 1.57785);
            editRadioButton.setLayoutY(dbY.getValue() / 1.51464);
            addIfMinRadioButton.setLayoutY(dbY.getValue() / 1.4563);
            removeGreaterRadioButton.setLayoutY(dbY.getValue() / 1.40228);
            removeLowerRadioButton.setLayoutY(dbY.getValue() / 1.35027);

            contentTable.setLayoutY(dbY.getValue() / 9.83);
            userText.setLayoutY(dbY.getValue() / 16.66102);
            usernameTextBlock.setLayoutY(dbY.getValue() / 13.10667);
            ruleTable.setLayoutY(dbY.getValue() / 1.38646);
        });
    }

    public void autoScalingSecond() {
        fillMap();
        objectTable.prefWidthProperty().bind(secondMainScreen.widthProperty().divide(1.26698));
        idColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(30.285714));
        nameColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(15.8209));
        xColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(17.096774193548387));
        yColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(18.59649));
        creationDateColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(11.2766));
        areaColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(23.04348));
        populationColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(14.72));
        metersAboveSeaLevelColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(7.6259));
        climateColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(14.52055));
        governmentColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(10.70707));
        standardOfLivingColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(8.61789));
        humanNameColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(10.6));
        usernameColumnSecond.prefWidthProperty().bind(objectTable.widthProperty().divide(12));

        DoubleBinding dbX = secondMainScreen.widthProperty().subtract(0);
        dbX.addListener((ov, t, t1) -> objectTable.setLayoutX(dbX.getValue() / 10.02239));
    }
}
