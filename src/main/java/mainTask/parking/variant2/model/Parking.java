package mainTask.parking.variant2.model;

import java.util.concurrent.Semaphore;

public class Parking {
    private boolean[] isParkingPlacesFree;
    private Semaphore semaphore;

    public Parking(int parkingCapacity) {
        this.isParkingPlacesFree = new boolean[parkingCapacity];
        this.semaphore = new Semaphore(parkingCapacity, true);
    }

    public boolean[] getIsParkingPlacesFree() {
        return isParkingPlacesFree;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
