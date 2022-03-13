package Processing;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class OperationManager {
    private static final Queue<String> queue = new LinkedList<>();
    private final Scanner scanner;

    public OperationManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getLine() {
        if (getQueue().isEmpty()) {
            return scanner.nextLine();
        }
        return getQueue().poll();
    }

    public static Queue<String> getQueue() {
        return queue;
    }
}
