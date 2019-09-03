package threadSynchronized;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class PositionWriter implements Runnable {
    private final Position position;
    private final StampedLock stampedLock;

    public PositionWriter(Position position, StampedLock stampedLock) {
        this.position = position;
        this.stampedLock = stampedLock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            long writeLock = stampedLock.writeLock();
            try {
                System.out.printf("Writer: lock acquired %d%n", writeLock);
                position.setX(position.getX() + 1);
                position.setY(position.getY() + 1);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                stampedLock.unlock(writeLock);
                System.out.printf("Writer: lock released %d%n", writeLock);
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
