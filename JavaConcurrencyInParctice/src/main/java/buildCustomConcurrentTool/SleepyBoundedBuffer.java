package buildCustomConcurrentTool;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SleepyBoundedBuffer<V> extends BaseBoundBuffer<V> {
    public SleepyBoundedBuffer(int c) {
        super(c);
    }

    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(1000);
        }
    }

    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isEmpty()) {
                    return doTake();
                }
            }
            Thread.sleep(1000);
        }
    }
}
