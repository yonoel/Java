package threadSynchronized;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class OptimisticReader implements Runnable {
    public static void main(String[] args) {
        Position position = new Position();
        StampedLock stampedLock = new StampedLock();
        Thread wirte = new Thread(new PositionWriter(position, stampedLock));
        Thread reader = new Thread(new PositionReader(position, stampedLock));
        Thread optimistic = new Thread(new OptimisticReader(position, stampedLock));

        wirte.start();
        reader.start();
        optimistic.start();

        try {
            wirte.join();
            reader.join();
            optimistic.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private final Position position;
    private final StampedLock stampedLock;

    public OptimisticReader(Position position, StampedLock stampedLock) {
        this.position = position;
        this.stampedLock = stampedLock;
    }

    @Override
    public void run() {
        long stamp;
        for (int i = 0; i < 100; i++) {
            try {
                stamp = stampedLock.tryOptimisticRead();
                int x = position.getX();
                int y = position.getY();
                if (stampedLock.validate(stamp)) {
                    System.out.printf("OptimisticReader: %d - (%d,%d) %n", stamp, x, y);
                } else {
                    System.out.printf("OptimisticReader: %d - Not free %n", stamp);
                }
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
