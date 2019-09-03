package threadSynchronized;

import com.sun.org.apache.regexp.internal.RE;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class PositionReader implements Runnable {
    private final Position position;
    private final StampedLock stampedLock;

    public PositionReader(Position position, StampedLock stampedLock) {
        this.position = position;
        this.stampedLock = stampedLock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            long readLock = stampedLock.readLock();
            try {
                System.out.printf("Reader: %d - (%d,%d) %n",readLock,position.getX(),position.getY());
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                stampedLock.unlock(readLock);
                System.out.printf("Reader: %d - lock released %n", readLock);
            }
        }
    }
}
