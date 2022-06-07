package mainTask.parking.v2;

import java.util.LinkedList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Parking> parkingList = new LinkedList<>();
        parkingList.add(new Parking(5));
        parkingList.add(new Parking(4));
        parkingList.add(new Parking(7));

        for (int i = 1; i <= 20; i++) {
            new Thread(new Car(i, parkingList)).start();
        }
    }
}
