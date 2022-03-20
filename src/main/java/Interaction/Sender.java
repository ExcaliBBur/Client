package Interaction;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Class for sending serialized data to server app.
 */
public class Sender {
    private final DatagramSocket datagramSocket;

    /**
     * Constructor, gets all necessary things.
     *
     * @param datagramSocket socket to transfer data
     */
    public Sender(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    /**
     * Sends data to server app.
     *
     * @param data          serialized object to transfer
     * @param socketAddress client address
     * @throws IOException caused by operations with datagram socket
     */
    public void sendResponse(byte[] data, SocketAddress socketAddress) throws IOException {
        byte[] array = new byte[4096];
        DatagramPacket datagramPacket = new DatagramPacket(array, array.length, socketAddress);
        datagramPacket.setData(data);
        this.getDatagramSocket().send(datagramPacket);
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }
}
