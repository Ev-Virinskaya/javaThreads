package optionalTask.variant3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Airport {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            Runnable plane = new Plane(i);
            executor.execute(plane);
        }
        executor.shutdown();
    }
}
