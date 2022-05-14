package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

public class MainScreenController<T> {
    private List<T> base;
    private final ObservableList<T> collection = FXCollections.observableArrayList(base);

    @FXML
    private TableView<T> table = new TableView<>(collection);

    public ObservableList<T> getCollection() {
        return collection;
    }

    public void setBase(List<T> base) {
        this.base = base;
    }

}
