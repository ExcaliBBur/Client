package Models;

import Interfaces.Updateable;
import javafx.collections.ObservableList;

public abstract class StorageController<T> implements Updateable<T> {
    private ObservableList<T> collection;

    public ObservableList<T> getCollection() {
        return collection;
    }

    public void setCollection(ObservableList<T> collection) {
        this.collection = collection;
    }
}
