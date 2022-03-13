package Interaction;

import Data.Response;

import java.io.*;

public class Parser {
    public static Object parseFrom(byte[] data) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInput in = new ObjectInputStream(byteArrayInputStream);

            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] parseTo(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(6400);
        ObjectOutput out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeObject(response);
        return byteArrayOutputStream.toByteArray();
    }
}
