package Controllers;

import Models.City;
import Models.StorageController;
import Models.User;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Collection;

public class MainScreenController extends StorageController<City> {
    private User user;

    @FXML
    private TableView<City> table;

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

    public void setUser(User user) {
        this.user = user;
    }

    //TODO СТОИТ ЗАДУМАТЬСЯ НАД ТЕМ, ЧТОБЫ ОБЪЕДИНИТЬ ВСЕ СЕТТЕРЫ В КАКОЙ-НИБУДЬ МЕТОД, КОТОРЫЙ БЫ УСТАНАВЛИВАЛ ВСЕ ТРЕБУЕМЫЕ ЗНАЧЕНИЯ.

    @FXML
    public void initialize() {
        this.idColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        this.nameColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        this.xColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getCoordinates().getFirstCoordinates()));
        this.yColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getCoordinates().getSecondCoordinates()));
        this.creationDateColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getCreationDate().toString()
                        .replace("T", " ")));
        this.areaColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getArea()));
        this.populationColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getPopulation()));
        this.metersAboveSeaLevelColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getMeters()));
        this.climateColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getClimate()));
        this.governmentColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getGovernment()));
        this.standardOfLivingColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getStandardOfLiving()));
        this.humanNameColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getGovernor().getHumanName()));

        this.setCollection(FXCollections.observableArrayList());
    }

    public void updateContents(Collection<City> collection) {
        this.getCollection().setAll(collection);
        table.getItems().setAll(this.getCollection());
    }
}
