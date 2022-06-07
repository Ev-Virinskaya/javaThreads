package mainTask.parking.v2;

import java.util.concurrent.Semaphore;

public class Parking {
    private boolean[] parkingPlaces;
    private Semaphore semaphore;

    public Parking(int parkingCapacity) {
        this.parkingPlaces = new boolean[parkingCapacity];
        this.semaphore = new Semaphore(parkingCapacity, true);
    }

    public boolean[] getParkingPlaces() {
        return parkingPlaces;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
