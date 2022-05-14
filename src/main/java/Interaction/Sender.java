package Interaction;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Class for sending serialized data to server app.
 */
public class Sender {
    /**
     * Sends data to server app.
     *
     * @param data          serialized object to transfer
     * @param socketAddress client address
     */
    public static void sendResponse(DatagramSocket datagramSocket, byte[] data, SocketAddress socketAddress) {
        try {
            byte[] array = new byte[4096];
            DatagramPacket datagramPacket = new DatagramPacket(array, array.length, socketAddress);
            datagramPacket.setData(data);
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
