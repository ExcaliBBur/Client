package Realisation;

import Interfaces.IHasher;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword implements IHasher {
    @Override
    public String hash(String originalString) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            return new String(encodedHash, StandardCharsets.UTF_8).trim();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
