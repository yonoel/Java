package customPackage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
    public static void main(String[] args) throws Exception {
        final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        Runnable read = () -> {
            readLock.lock();
            System.out.printf("%s上锁%n", Thread.currentThread().getName());

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
        };

        Runnable write = () -> {
            writeLock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
        };
        Thread a = new Thread(read);
        Thread b = new Thread(read);
        Thread c = new Thread(write);
        Thread d = new Thread(read);
        a.start();
        c.start();
        b.start();
        TimeUnit.SECONDS.sleep(1);
        d.start();

        a.join();
        b.join();
        c.join();
        d.join();

    }
}
