package Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Class for serializing data.
 */
public class Serializer {
    /**
     * Serialize data to json format.
     *
     * @param object object to serialize
     * @return string json serialized object
     */
    public static String serialize(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
