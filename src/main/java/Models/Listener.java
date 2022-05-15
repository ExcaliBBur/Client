package Models;

import Interaction.Receiver;

import java.net.DatagramSocket;

public abstract class Listener<T> extends Thread {
    private final Receiver receiver;
    private StorageController<T> controller;

    public Listener(DatagramSocket channel, StorageController<T> controller) {
        this.receiver = new Receiver(channel);
        this.controller = controller;
    }

    public Listener(DatagramSocket channel) {
        this.receiver = new Receiver(channel);
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public StorageController<T> getController() {
        return controller;
    }

    public void setController(StorageController<T> controller) {
        this.controller = controller;
    }
}
