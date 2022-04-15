package Interfaces;

/**
 * Represents hasher contract.
 */
public interface IHasher {
    /**
     * Hash string in the following format.
     *
     * @param originalString string to hash
     * @return hashed string
     */
    String hash(String originalString);
}
