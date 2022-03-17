package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * It is used to read the contents of the transferred file.
 */
public class TextReader {
    /**
     * Reads file.
     *
     * @param file transferred file
     * @return file contents
     */
    public static String readFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        int counter;
        while ((counter = reader.read()) != -1) {
            stringBuilder.append((char) counter);
        }
        return stringBuilder.toString().trim();
    }
}