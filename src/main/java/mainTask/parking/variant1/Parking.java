package mainTask.parking.variant1;

import java.util.concurrent.Semaphore;

public class Parking {
    protected static final boolean[] isParkingPlaceFree = new boolean[5];
    protected static final Semaphore semaphore = new Semaphore(5,true);


    public static void main(String[] args) {
        for (int i = 1; i <= 7; i++) {
            new Thread(new Car(i)).start();
        }
    }
}