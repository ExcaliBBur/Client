package Models;

import Interaction.Receiver;
import javafx.collections.ObservableList;

import java.net.DatagramSocket;

public abstract class Listener<T> implements Runnable {
    private final Receiver receiver;
    private final ObservableList<T> collection;

    public Listener(DatagramSocket channel, ObservableList<T> collection) {
        this.receiver = new Receiver(channel);
        this.collection = collection;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public ObservableList<T> getCollection() {
        return collection;
    }
}
