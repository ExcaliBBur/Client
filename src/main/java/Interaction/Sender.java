package Interaction;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Class for sending serialized data to server app.
 */
public class Sender {
    private final DatagramSocket channel;
    private final SocketAddress address;


    public Sender(DatagramSocket channel, SocketAddress address) {
        this.channel = channel;
        this.address = address;
    }

    /**
     * Sends data to server app.
     *
     * @param data serialized object to transfer
     */
    public void sendResponse(byte[] data) {
        try {
            byte[] array = new byte[4096];
            DatagramPacket datagramPacket = new DatagramPacket(array, array.length, this.address);
            datagramPacket.setData(data);

            this.channel.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DatagramSocket getChannel() {
        return channel;
    }
}
