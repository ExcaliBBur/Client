package Realisation;

import Exceptions.InputException;
import Interaction.Parser;
import Models.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StorageListener extends Listener<City> {
    private final Queue<ServerDTO<City>> answersQueue;
    private final ReentrantReadWriteLock lock;

    public StorageListener(DatagramSocket channel, StorageController<City> controller, Queue<ServerDTO<City>>
            answerQueue, ReentrantReadWriteLock lock) {
        super(channel, controller);
        this.answersQueue = answerQueue;
        this.lock = lock;
    }

    public StorageListener(DatagramSocket channel, Queue<ServerDTO<City>> answerQueue, ReentrantReadWriteLock lock) {
        super(channel);
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
                    concatenator.reset();
                    if (serverDTO.getDtoType().equals(ServerDTO.DTOType.RESPONSE)) {
                        writeLock.lock();
                        this.answersQueue.add(serverDTO);
                        writeLock.unlock();
                    } else {
                        writeLock.lock();
                        this.getController().updateContents(serverDTO.getCollection());
                        writeLock.unlock();

                        //TODO ЭТО ПОКА ЧТО ЗАТЫЧКА, СТОИТ ПОДУМАТЬ, КАК ЛУЧШЕ ОФОРМИТЬ МОМЕНТ ПОДМЕНЫ СОДЕРЖИМОГО OBSERVABLE LIST.
                        //TODO REGISTER - LOGIN ЛОМАЕТ ВСЁ.
                    }
                } catch (InputException.ServerUnavailableException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ReentrantReadWriteLock getLock() {
        return lock;
    }
}
