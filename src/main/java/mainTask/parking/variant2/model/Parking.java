package mainTask.parking.variant2.model;

import java.util.concurrent.Semaphore;

public class Parking {
    private boolean[] isParkingPlaceFree;
    private Semaphore semaphore;

    public Parking(int parkingCapacity) {
        this.isParkingPlaceFree = new boolean[parkingCapacity];
        this.semaphore = new Semaphore(parkingCapacity, true);
    }

    public boolean[] getIsParkingPlaceFree() {
        return isParkingPlaceFree;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
