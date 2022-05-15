package Controllers;

import Models.City;
import Models.StorageController;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Arrays;
import java.util.Collection;

public class MainScreenController extends StorageController<City> {
    private final ObservableList<City> collection = FXCollections.observableArrayList();
    private User user;

    @FXML
    private TableView<City> table;

    @FXML
    private TableColumn<City, String> idColumn;

    @FXML
    private TableColumn<City, String> nameColumn;

    @FXML
    private TableColumn<City, String> coordinatesColumn;

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
    private TableColumn<City, String> climateColumn;

    @FXML
    private TableColumn<City, String> governmentColumn;

    @FXML
    private TableColumn<City, String> standardOfLivingColumn;

    @FXML
    private TableColumn<City, String> governorColumn;

    @FXML
    private TableColumn<City, String> humanNameColumn;

    public void setUser(User user) {
        this.user = user;
    }

    //TODO СТОИТ ЗАДУМАТЬСЯ НАД ТЕМ, ЧТОБЫ ОБЪЕДИНИТЬ ВСЕ СЕТТЕРЫ В КАКОЙ-НИБУДЬ МЕТОД, КОТОРЫЙ БЫ УСТАНАВЛИВАЛ ВСЕ ТРЕБУЕМЫЕ ЗНАЧЕНИЯ.

    @FXML
    public void initialize() {
        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.xColumn.setCellValueFactory(new PropertyValueFactory<>("firstCoordinates"));
        this.yColumn.setCellValueFactory(new PropertyValueFactory<>("secondCoordinates"));
        this.creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        this.areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        this.populationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));
        this.metersAboveSeaLevelColumn.setCellValueFactory(new PropertyValueFactory<>("meters"));
        this.climateColumn.setCellValueFactory(new PropertyValueFactory<>("climate"));
        this.governmentColumn.setCellValueFactory(new PropertyValueFactory<>("government"));
        this.standardOfLivingColumn.setCellValueFactory(new PropertyValueFactory<>("standardOfLiving"));
        this.humanNameColumn.setCellValueFactory(new PropertyValueFactory<>("humanName"));

        table.getItems().setAll(collection);
    }

    public void updateContents(Collection<City> collection) {
        this.collection.clear();
        this.collection.addAll(collection);

        System.out.println(collection.size());
    }
}
