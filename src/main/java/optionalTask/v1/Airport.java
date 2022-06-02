package optionalTask.v1;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    public static final List<Plane> schedule = new ArrayList<>();

    public static void main(String[] args) {
        addPlanes();
        Line one = new Line("1");
        Line two = new Line("2");
        Line three = new Line("3");
        Line four = new Line("4");
        Line five = new Line("5");

        one.start();
        two.start();
        three.start();
        four.start();
        five.start();
    }

    private static class Line extends Thread {
        private String nameLine;

        public Line( String nameLine) {
            this.nameLine = nameLine;
        }


        @Override
        public void run() {
            while (!schedule.isEmpty()) {

                Plane plane;
                synchronized (schedule) {
                    plane = schedule.get(0);
                    schedule.remove(0);
                }
                System.out.println("Полоса " + nameLine + " \"приняла\" самолет " + plane.getName());
                plane.runwayExit();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                plane.departure();
                System.out.println("Полоса " + nameLine + " освободилась");

            }
        }
    }

    private static void addPlanes() {
        for (int i = 0; i < 10; i++) {
            Airport.schedule.add(new Plane("plane " + i));
        }
    }
}
