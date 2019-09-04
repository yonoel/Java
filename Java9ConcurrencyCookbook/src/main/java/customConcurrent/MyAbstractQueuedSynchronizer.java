package customConcurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {
    public static void main(String[] args) {
        MyLock lock = new MyLock();

        for (int i = 0; i < 10; i++) {
            MyLockTask task = new MyLockTask(lock, "task -" + i);
            new Thread(task).start();
        }

        boolean value;
        do {

            try {
                value = lock.tryLock(1, TimeUnit.SECONDS);
                if (!value)
                    System.out.printf("trying to get the lock %n");
            } catch (InterruptedException e) {
                e.printStackTrace();
                value = false;
            }
        } while (!value);
        System.out.println("get the lock");
        lock.unlock();
    }

    private final AtomicInteger state;

    public MyAbstractQueuedSynchronizer() {
        state = new AtomicInteger(0);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        return state.compareAndSet(0, 1);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return state.compareAndSet(1, 0);
    }
}
