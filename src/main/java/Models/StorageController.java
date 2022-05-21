package Models;

import Interfaces.Updateable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class StorageController<T> extends Controller implements Updateable<T> {
    private final ObservableList<T> collection = FXCollections.observableArrayList();

    public ObservableList<T> getCollection() {
        return collection;
    }
}
