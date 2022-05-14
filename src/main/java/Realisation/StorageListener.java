package Realisation;

import Exceptions.InputException;
import Interaction.Parser;
import Models.DTOWrapper;
import Models.Listener;
import Models.ServerDTO;
import javafx.collections.ObservableList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.util.Queue;

public class StorageListener<T> extends Listener<T> {
    private final Queue<ServerDTO<T>> answersQueue;

    public StorageListener(DatagramSocket channel, ObservableList<T> collection, Queue<ServerDTO<T>> answerQueue) {
        super(channel, collection);
        this.answersQueue = answerQueue;
    }

    public ServerDTO<T> getNextAnswer() {
        return this.answersQueue.poll();
    }

    @Override
    public void run() {
        try (ByteArrayOutputStream concatenator = new ByteArrayOutputStream()) {
            DTOWrapper dtoWrapper;
            ServerDTO<T> serverDTO;

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    do {
                        dtoWrapper = (DTOWrapper) Parser.parseFrom(this.getReceiver().getResponse());
                        concatenator.write(dtoWrapper.getData());
                    } while (!dtoWrapper.isTerminating());

                    serverDTO = (ServerDTO<T>) Parser.parseFrom(concatenator.toByteArray());
                    if (serverDTO.getDtoType().equals(ServerDTO.DTOType.RESPONSE)) {
                        this.answersQueue.add(serverDTO);
                    } else {
                        this.getCollection().clear();
                        this.getCollection().addAll(serverDTO.getCollection());

                        //TODO ЭТО ПОКА ЧТО ЗАТЫЧКА, СТОИТ ПОДУМАТЬ, КАК ЛУЧШЕ ОФОРМИТЬ МОМЕНТ ПОДМЕНЫ СОДЕРЖИМОГО OBSERVABLE LIST.
                    }
                } catch (InputException.ServerUnavailableException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
