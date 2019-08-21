package explicitLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TimedLockExample {
    private Lock lock;

    public boolean trySendOnSharedLine(String msg, long timeout, TimeUnit unit) throws InterruptedException {
        long nano = unit.toNanos(timeout);
        if (!lock.tryLock(nano, TimeUnit.NANOSECONDS))
            return false;
        try {
            return sendOnSharedLine(msg);
        } finally {
            lock.unlock();
        }
    }

    private boolean sendOnSharedLine(String msg) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            return cancellabeleSendOnSharedLine(msg);
        } finally {
            lock.unlock();
        }
    }

    private boolean cancellabeleSendOnSharedLine(String msg) {
        return false;
    }
}
