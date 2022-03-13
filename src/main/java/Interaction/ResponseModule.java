package Interaction;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ResponseModule {
    private final DatagramSocket datagramSocket;

    public ResponseModule(int port, InetAddress address) throws SocketException {
        datagramSocket = new DatagramSocket(port, address);
    }

    public byte[] getResponse() {
        try {
            byte[] array = new byte[4096];
            DatagramPacket datagramPacket = new DatagramPacket(array, array.length);
            this.getDatagramSocket().receive(datagramPacket);

            return datagramPacket.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendResponse(byte[] data) throws IOException {
        byte[] array = new byte[4096];
        DatagramPacket datagramPacket = new DatagramPacket(array, array.length, InetAddress.getByName("localhost"),
                6666);
        datagramPacket.setData(data);
        this.getDatagramSocket().send(datagramPacket);
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }
}
