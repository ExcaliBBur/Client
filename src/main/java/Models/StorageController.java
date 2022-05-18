package Models;

import Interfaces.Updateable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class StorageController<T> implements Updateable<T> {
    private ObservableList<T> collection = FXCollections.observableArrayList();

    public ObservableList<T> getCollection() {
        return collection;
    }

    public void setCollection(ObservableList<T> collection) {
        this.collection = collection;
    }
}
