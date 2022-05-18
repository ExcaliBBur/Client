package Controllers;

import Models.City;
import Models.Rule;
import Models.StorageController;
import Models.User;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

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
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button removeRuleButton;

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
    }

    public void updateContents(Collection<City> collection) {
        this.getCollection().setAll(collection);

        this.fillCityTable();
    }

    private void fillCityTable() {
        Collection<City> collection = new ArrayList<>(this.getCollection());
        for (Rule rule : this.ruleList) {
            collection = rule.transform(collection);
        }

        this.sortedCityList.setAll(collection);
        contentTable.getItems().setAll(sortedCityList);
    }

    public void updateRuleContents(Collection<Rule> ruleList) {
        this.ruleList.addAll(ruleList);
        this.ruleTable.getItems().setAll(this.ruleList);

        this.fillCityTable();
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

            int id = this.ruleTable.getSelectionModel().getFocusedIndex();
            if (id >= 0) {
                sortFilterController.setID(this.ruleTable.getSelectionModel().getFocusedIndex());
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }

            additionalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Dick!");
            alert.showAndWait();

            //TODO ERROR!
        }
    }

    @FXML
    private void removeRule() {
        try {
            this.ruleList.remove(this.ruleTable.getSelectionModel().getFocusedIndex());
            this.ruleTable.getItems().setAll(ruleList);

            fillCityTable();
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Dick!");
            alert.showAndWait();

            //TODO ERROR!
        }
    }
}
