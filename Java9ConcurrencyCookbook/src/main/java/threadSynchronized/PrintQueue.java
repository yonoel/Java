package threadSynchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
    private Lock lock;

    public PrintQueue(boolean fairMode) {
        lock = new ReentrantLock(fairMode);
    }

    public void printJob(Object doc) {
        lock.lock();
        try {
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ":printQueue: printing a job during " + (duration / 1000) + " second");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        lock.lock();
        try {
            long duration = (long) (Math.random() * 10000);
            System.out.printf("%s :printQueue: printing a job during %d second %n", Thread.currentThread().getName(), (duration / 1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
