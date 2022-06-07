package mainTask.parking.v2;

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
                    synchronized (currentParking.getParkingPlaces()) {
                        for (int i = 0; i < currentParking.getParkingPlaces().length; i++) {
                            if (!currentParking.getParkingPlaces()[i]) {
                                currentParking.getParkingPlaces()[i] = true;
                                parkingNumber = i;
                                System.out.printf("Машина №%d припаркована на парковке %d  на месте %d.\n", carId, j + 1, i + 1);
                                break;
                            }
                        }
                    }
                    Thread.sleep((long) (Math.random() * 10000));

                    synchronized (currentParking.getParkingPlaces()) {
                        currentParking.getParkingPlaces()[parkingNumber] = false;
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

//    @Override
//    public void run() {
//        System.out.printf("Машина №%d подъехала к парковке.\n", carId);
//        try {
//            if (Parking.semaphore.tryAcquire(1, TimeUnit.SECONDS)) {
//                int parkingNumber = -1;
//                synchronized (Parking.parkingPlaces) {
//                    for (int i = 0; i < Parking.parkingPlaces.length; i++) {
//                        if (!Parking.parkingPlaces[i]) {
//                            Parking.parkingPlaces[i] = true;
//                            parkingNumber = i;
//                            System.out.printf("Машина №%d припаркована на месте %d.\n", carId, i + 1);
//                            break;
//                        }
//                    }
//                }
//                Thread.sleep((long) (Math.random() * 10000));
//
//                synchronized (Parking.parkingPlaces) {
//                    Parking.parkingPlaces[parkingNumber] = false;
//                }
//                Parking.semaphore.release();
//                System.out.printf("Машина №%d уехала с парковки.\n", carId);
//            } else {
//                System.out.printf("Машина %d уехала на другую парковку\n", carId);
//            }
//        }   catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
