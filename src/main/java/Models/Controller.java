package Models;

import Exceptions.InputException;
import Interaction.Sender;
import Main.Client;
import Realisation.StorageListener;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.ListResourceBundle;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class Controller {
    private ListResourceBundle locale;
    private Sender sender;
    private StorageListener listener;
    private boolean isWaiting;

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

    public ListResourceBundle getLocale() {
        return locale;
    }

    public void setLocale(ListResourceBundle locale) {
        this.locale = locale;
    }

    public void setWaiting(boolean isWaiting) {
        this.isWaiting = isWaiting;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void alert(String message, Alert.AlertType type) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type);

            alert.setContentText(message);
            alert.setHeaderText(Client.resourceFactory.getResources().getString(type.name().toLowerCase()));
            alert.setTitle(Client.resourceFactory.getResources().getString(type.name().toLowerCase()));

            alert.showAndWait();
        });
    }

    public ServerDTO<City> blockGetAnswer() {
        ReentrantReadWriteLock.ReadLock readLock = listener.getLock().readLock();
        ServerDTO<City> serverDTO;
        long currentTime = System.currentTimeMillis();
        this.listener.cleanQueue();

        do {
            readLock.lock();
            serverDTO = listener.getNextAnswer();
            readLock.unlock();
        } while (serverDTO == null && (System.currentTimeMillis() - currentTime < 1000));

        if (serverDTO == null) {
            alert(new InputException.ServerUnavailableException().getMessage(),
                    Alert.AlertType.ERROR);
        }
        return serverDTO;
    }
}
