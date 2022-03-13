package Utilities;

import Data.Collectables;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Serializer {
    public String serialize(Collectables collectable) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(collectable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
