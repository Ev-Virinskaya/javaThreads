package optionalTask.variant3;

public class Plane implements Runnable {
    private int planeNumber;

    public Plane(int planeNumber) {
        this.planeNumber = planeNumber;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Полоса %s приняла самолет №%d\n", Thread.currentThread().getName(), planeNumber);
            System.out.printf("Самолет %d начал выход на полосу %s\n", planeNumber, Thread.currentThread().getName());

            Thread.sleep(300);

            System.out.printf("Самолет №%d взлетел\n", planeNumber);
            System.out.printf("Полоса %s освободилась\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
