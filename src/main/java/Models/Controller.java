package Models;

import Exceptions.InputException;
import Interaction.Sender;
import Realisation.StorageListener;
import javafx.scene.control.Alert;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class Controller {
    private Sender sender;
    private StorageListener listener;

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public StorageListener getListener() {
        return listener;
    }

    public void setListener(StorageListener listener) {
        this.listener = listener;
    }

    public void alert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);

        alert.setContentText(message);
        alert.showAndWait();
    }

    public ServerDTO<City> blockGetAnswer() {
        ReentrantReadWriteLock.ReadLock readLock = listener.getLock().readLock();
        ServerDTO<City> serverDTO;
        long currentTime = System.currentTimeMillis();

        do {
            readLock.lock();
            serverDTO = listener.getNextAnswer();
            readLock.unlock();
        } while (serverDTO == null && (System.currentTimeMillis() - currentTime < 1000));

        if (serverDTO == null) {
            alert(new InputException.ServerUnavailableException().getMessage(), Alert.AlertType.ERROR);
        }

        return serverDTO;
    }
}
