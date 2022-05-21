package Interaction;

import Exceptions.InputException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.net.SocketTimeoutException;

/**
 * Class for receiving data from server app.
 */
public class Receiver {
    private final DatagramSocket datagramSocket;

    /**
     * Constructor, gets all necessary things.
     *
     * @param datagramSocket socket to get data from
     */
    public Receiver(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    /**
     * Gets byte data from datagram.
     *
     * @return byte data
     * @throws InputException.ServerUnavailableException caused by unavailable server
     */
    public byte[] getResponse() throws InputException.ServerUnavailableException {
        try {
            byte[] array = new byte[4096];
            DatagramPacket datagramPacket = new DatagramPacket(array, array.length);
            try {
                datagramSocket.receive(datagramPacket);
            } catch (SocketTimeoutException e) {
                throw new InputException.ServerUnavailableException();
            }
            return datagramPacket.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }
}
