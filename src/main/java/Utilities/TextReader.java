package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {
    public String readFile(File file) throws IOException {
        try (FileReader fileReader = new FileReader(file); BufferedReader reader = new BufferedReader(fileReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            int counter;
            while ((counter = reader.read()) != -1) {
                stringBuilder.append((char) counter);
            }
            return stringBuilder.toString().trim();
        }
    }
}