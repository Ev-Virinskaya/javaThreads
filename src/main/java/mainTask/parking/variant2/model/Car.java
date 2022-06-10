package mainTask.parking.variant2.model;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    private int carId;
    private List<Parking> availableParking;

    public Car(int carId, List<Parking> availableParking) {
        this.carId = carId;
        this.availableParking = availableParking;
    }

    @Override
    public void run() {
        for (int j = 0; j < availableParking.size(); j++) {
            System.out.printf("Машина №%d подъехала к парковке %d.\n", carId, j + 1);
            Parking currentParking = availableParking.get(j);
            try {
                if (currentParking.getSemaphore().tryAcquire(1, TimeUnit.SECONDS)) {
                    int parkingNumber = -1;
                    synchronized (currentParking.getIsParkingPlacesFree()) {
                        for (int i = 0; i < currentParking.getIsParkingPlacesFree().length; i++) {
                            if (!currentParking.getIsParkingPlacesFree()[i]) {
                                currentParking.getIsParkingPlacesFree()[i] = true;
                                parkingNumber = i;
                                System.out.printf("Машина №%d припаркована на парковке %d  на месте %d.\n", carId, j + 1, i + 1);
                                break;
                            }
                        }
                    }
                    Thread.sleep((long) (Math.random() * 10000));

                    synchronized (currentParking.getIsParkingPlacesFree()) {
                        currentParking.getIsParkingPlacesFree()[parkingNumber] = false;
                    }
                    currentParking.getSemaphore().release();
                    System.out.printf("Машина №%d уехала с парковки %d.\n", carId, j + 1);
                    break;
                } else {
                    System.out.printf("Машина %d уехала на другую парковку\n", carId);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}