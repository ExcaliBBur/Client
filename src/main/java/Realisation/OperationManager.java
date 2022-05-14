package Realisation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Class for reading data.
 */
public class OperationManager {
    private static final Queue<String> queue = new LinkedList<>();
    private final Scanner scanner;

    /**
     * Constructor, gets all necessary things.
     *
     * @param scanner tool for reading data.
     */
    public OperationManager(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Returns next command to execute.
     *
     * @return String command
     */
    public String getLine() {
        if (getQueue().isEmpty()) {
            return scanner.nextLine().trim();
        }
        return getQueue().poll();
    }

    public static Queue<String> getQueue() {
        return queue;
    }
}
