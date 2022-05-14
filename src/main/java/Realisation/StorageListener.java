package Realisation;

import Exceptions.InputException;
import Interaction.Parser;
import Models.City;
import Models.DTOWrapper;
import Models.Listener;
import Models.ServerDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StorageListener extends Listener {
    private final Queue<ServerDTO<City>> answersQueue;
    private final ReentrantReadWriteLock lock;

    public StorageListener(DatagramSocket channel, List<City> collection, Queue<ServerDTO<City>> answerQueue,
                           ReentrantReadWriteLock lock) {
        super(channel, collection);
        this.answersQueue = answerQueue;
        this.lock = lock;
    }

    public ServerDTO<City> getNextAnswer() {
        return this.answersQueue.poll();
    }

    @Override
    public void run() {
        try (ByteArrayOutputStream concatenator = new ByteArrayOutputStream()) {
            DTOWrapper dtoWrapper;
            ServerDTO<City> serverDTO;
            ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    do {
                        dtoWrapper = (DTOWrapper) Parser.parseFrom(this.getReceiver().getResponse());
                        concatenator.write(dtoWrapper.getData());
                    } while (!dtoWrapper.isTerminating());

                    serverDTO = (ServerDTO<City>) Parser.parseFrom(concatenator.toByteArray());
                    if (serverDTO.getDtoType().equals(ServerDTO.DTOType.RESPONSE)) {
                        writeLock.lock();
                        this.answersQueue.add(serverDTO);
                        writeLock.unlock();
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
