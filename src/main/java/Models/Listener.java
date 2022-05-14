package Models;

import Interaction.Receiver;

import java.net.DatagramSocket;
import java.util.List;

public abstract class Listener extends Thread {
    private final Receiver receiver;
    private final List<City> collection;

    public Listener(DatagramSocket channel, List<City> collection) {
        this.receiver = new Receiver(channel);
        this.collection = collection;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public List<City> getCollection() {
        return collection;
    }
}
