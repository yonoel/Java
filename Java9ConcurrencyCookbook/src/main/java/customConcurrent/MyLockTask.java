package customConcurrent;

import java.util.concurrent.TimeUnit;

public class MyLockTask implements Runnable {
    private final MyLock lock;
    private final String name;

    public MyLockTask(MyLock lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        lock.lock();
        System.out.printf("task %s take the lock%n", name);
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.printf("task %s free the lock %n", name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
