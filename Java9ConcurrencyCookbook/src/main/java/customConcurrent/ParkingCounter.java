package customConcurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class ParkingCounter extends AtomicInteger {
    public static void main(String[] args) throws Exception {
        ParkingCounter counter = new ParkingCounter(5);
        Sensor1 sensor1 = new Sensor1(counter);
        Sensor2 sensor2 = new Sensor2(counter);

        Thread s1 = new Thread(sensor1);
        Thread s2 = new Thread(sensor2);
        s1.start();
        s2.start();

        s1.join();
        s2.join();

    }

    private final int max;

    public ParkingCounter(int max) {
        set(0);
        this.max = max;
    }

    public boolean carIn() {
        for (; ; ) {
            int val = get();
            if (val == max) {
                System.out.println("the parking is full ");
                return false;
            } else {
                int newVal = val + 1;
                boolean changed = compareAndSet(val, newVal);
                if (changed) {
                    System.out.println(" a car is in");
                    return true;
                }
            }
        }
    }

    public boolean carOut() {
        for (; ; ) {
            int val = get();
            if (val == 0) {
                System.out.println("the parking is empty");
                return false;
            } else {
                int newVal = val - 1;
                boolean changed = compareAndSet(val, newVal);
                if (changed) {
                    System.out.println("a car has gone");
                    return true;
                }
            }
        }
    }
}
