package optionalTask.variant2;

import java.util.concurrent.Semaphore;

public class Airport {
    private static final boolean[] LINES = new boolean[5];
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            new Thread(new Plane(i)).start();
        }
    }


    public static class Plane implements Runnable{
        private int planeNumber;

        public Plane(int planeNumber) {
            this.planeNumber = planeNumber;
        }

        @Override
        public void run() {

            try{
                SEMAPHORE.acquire();
                int lineNumber = -1;
                synchronized (LINES){
                    for (int i = 0; i < LINES.length; i++){
                        if(!LINES[i]){
                            LINES[i] = true;
                            lineNumber = i;
                            System.out.printf("Полоса №%d \"приняла\" самолет №%d\n", lineNumber + 1, planeNumber);
                            break;
                        }
                    }
                }
                System.out.printf("Самолет %d начал выход на полосу\n", planeNumber);
                Thread.sleep(300);
                synchronized (LINES){
                    LINES[lineNumber] = false;
                    System.out.printf("Самолет №%d взлетел\n", planeNumber);
                }
                SEMAPHORE.release();
                System.out.printf("Полоса №%d освободилась\n", lineNumber + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
