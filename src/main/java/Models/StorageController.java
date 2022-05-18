package Models;

import Interaction.Sender;
import Interfaces.Updateable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class StorageController<T> implements Updateable<T> {
    private ObservableList<T> collection = FXCollections.observableArrayList();
    private Sender sender;

    public ObservableList<T> getCollection() {
        return collection;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
