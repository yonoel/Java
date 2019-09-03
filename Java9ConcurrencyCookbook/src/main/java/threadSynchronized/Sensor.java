package threadSynchronized;

import java.util.concurrent.TimeUnit;

public class Sensor implements Runnable {
    public static void main(String[] args) {
        ParkingCash cash = new ParkingCash();
        ParkingStats stats = new ParkingStats(cash);
        System.out.printf(" parking simulator  %n");
        int processors = Runtime.getRuntime().availableProcessors() * 2;
        Thread[] threads = new Thread[processors];
        for (int i = 0; i < processors; i++) {
            Sensor sensor = new Sensor(stats);
            Thread thread = new Thread(sensor);
            thread.start();
            threads[i] = thread;
        }

        for (int i = 0; i < processors; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf(" number of cars : %d %n", stats.getNumberCars());
        System.out.printf(" number of motorcycles : %d %n", stats.getNumberMotorcycles());
        cash.close();

    }

    private ParkingStats stats;

    public Sensor(ParkingStats stats) {
        this.stats = stats;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            stats.carComeIn();
            stats.carComeIn();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stats.motoComeIn();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stats.carGoOut();
            stats.carGoOut();
            stats.motoGoOut();
        }
    }
}
