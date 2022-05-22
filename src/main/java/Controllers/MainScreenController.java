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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainScreenController extends StorageController<City> {
    private final ObservableList<Rule> ruleList = FXCollections.observableArrayList();
    private final ObservableList<City> sortedCityList = FXCollections.observableArrayList();
    private CommandManager commandManager;
    private User user;
    private HashMap<Shape, Integer> shapeMap = new HashMap<>();
    private List<City> list = new ArrayList<>();
    private boolean flag = true;
    private HashMap<String, Color> colorHashMap = new HashMap<>();
    private List<Integer> idList = new ArrayList<>();
    private Canvas canvas = new Canvas(1280, 980);

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
    private TableColumn<City, Integer> idColumn1;

    @FXML
    private TableColumn<City, String> nameColumn1;

    @FXML
    private TableColumn<City, City.Coordinates> coordinatesColumn1;

    @FXML
    private TableColumn<City, Double> xColumn1;

    @FXML
    private TableColumn<City, Float> yColumn1;

    @FXML
    private TableColumn<City, String> creationDateColumn1;

    @FXML
    private TableColumn<City, Integer> areaColumn1;

    @FXML
    private TableColumn<City, Integer> populationColumn1;

    @FXML
    private TableColumn<City, Integer> metersAboveSeaLevelColumn1;

    @FXML
    private TableColumn<City, City.Climate> climateColumn1;

    @FXML
    private TableColumn<City, City.Government> governmentColumn1;

    @FXML
    private TableColumn<City, City.StandardOfLiving> standardOfLivingColumn1;

    @FXML
    private TableColumn<City, City.Human> governorColumn1;

    @FXML
    private TableColumn<City, String> humanNameColumn1;

    @FXML
    private TableColumn<City, String> usernameColumn1;

    public void setUser(User user) {
        this.user = user;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
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
        this.idColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        idColumn1.setSortable(false);

        this.nameColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        nameColumn1.setSortable(false);

        this.xColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getCoordinates().getFirstCoordinates()));
        xColumn1.setSortable(false);

        this.yColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getCoordinates().getSecondCoordinates()));
        yColumn1.setSortable(false);

        this.creationDateColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getCreationDate().toString()
                        .replace("T", " ")));
        creationDateColumn1.setSortable(false);

        this.areaColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getArea()));
        areaColumn1.setSortable(false);

        this.populationColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getPopulation()));
        populationColumn1.setSortable(false);

        this.metersAboveSeaLevelColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getMeters()));
        metersAboveSeaLevelColumn1.setSortable(false);

        this.climateColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getClimate()));
        climateColumn1.setSortable(false);

        this.governmentColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getGovernment()));
        governmentColumn1.setSortable(false);

        this.standardOfLivingColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getStandardOfLiving()));
        standardOfLivingColumn1.setSortable(false);

        this.humanNameColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getGovernor().getHumanName()));
        humanNameColumn1.setSortable(false);

        this.usernameColumn1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getUsername()));
        usernameColumn1.setSortable(false);

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
        return Parser.parseTo(new ClientDTO(new Command.CommandData(keyWord, arguments), true, this.user));
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
        } catch (IOException e) {
            e.printStackTrace();
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

    public void fillMap() {
        boolean contains = false;
        Set keys = shapeMap.keySet();
        for (Object key : keys) {
            secondMainScreen.getChildren().remove(key);
        }
        shapeMap.clear();
        if (flag) {
            for (City city : sortedCityList) {
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
        list = sortedCityList.stream().sorted(Comparator.comparingInt(City::getArea).reversed())
                .collect(Collectors.toList());
        for (City city : list) {
            Shape circle = new Circle((city.getArea() / 50) * canvas.getScaleX(), colorHashMap.get(city.getUsername()));
            circle.setLayoutX(canvas.getWidth() / 2 + (city.getCoordinates().getFirstCoordinates()) * canvas.getScaleX());
            circle.setLayoutY(canvas.getHeight() / 2 + (city.getCoordinates().getSecondCoordinates()) * canvas.getScaleX());
            shapeMap.put(circle, city.getId());
            if (!idList.contains(city.getId())) {
                idList.add(city.getId());
                contains = true;
            }
            circle.setStroke(Color.BLACK);
            secondMainScreen.setOnScroll(this::mouseScroll);
            circle.setOnMouseClicked(this::mouseClicked);
            circle.setOnMouseEntered(this::mouseEntered);
            if (circle.getLayoutY() - city.getArea() / 50 * canvas.getScaleX() - 82 > 0) {
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
        for (City city : sortedCityList) {
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
        for (City city : sortedCityList) {
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
}
