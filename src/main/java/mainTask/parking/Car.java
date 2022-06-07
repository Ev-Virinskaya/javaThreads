package mainTask.parking;

import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    private int carId;

    public Car(int carId) {
        this.carId = carId;
    }

    @Override
    public void run() {
        System.out.printf("Машина №%d подъехала к парковке.\n", carId);
        try {
            if (Parking.semaphore.tryAcquire(1, TimeUnit.SECONDS)) {
                int parkingNumber = -1;
                synchronized (Parking.parkingPlaces) {
                    for (int i = 0; i < Parking.parkingPlaces.length; i++) {
                        if (!Parking.parkingPlaces[i]) {
                            Parking.parkingPlaces[i] = true;
                            parkingNumber = i;
                            System.out.printf("Машина №%d припаркована на месте %d.\n", carId, i + 1);
                            break;
                        }
                    }
                }
                Thread.sleep((long) (Math.random() * 10000));

                synchronized (Parking.parkingPlaces) {
                    Parking.parkingPlaces[parkingNumber] = false;
                }
                Parking.semaphore.release();
                System.out.printf("Машина №%d уехала с парковки.\n", carId);
            } else {
                System.out.printf("Машина %d уехала на другую парковку\n", carId);
            }
        }   catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
