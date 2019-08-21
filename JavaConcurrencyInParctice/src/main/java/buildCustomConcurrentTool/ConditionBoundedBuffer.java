package buildCustomConcurrentTool;

import net.jcip.annotations.GuardedBy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<T> {
    protected final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    @GuardedBy("lock")
    private final T[] items;
    @GuardedBy("lock")
    private int tail, head, count;

    public ConditionBoundedBuffer(int c) {
        items = (T[]) new Object[c];
    }

    public void put(T t) throws InterruptedException{
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[tail] = t;
            if (++tail == items.length)
                tail = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException{
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            T x = items[head];
            if (++ head == items.length){
                head = 0;
            }
            -- count;
            notFull.signal();
            return x;
        }finally {
            lock.unlock();
        }
    }
}
