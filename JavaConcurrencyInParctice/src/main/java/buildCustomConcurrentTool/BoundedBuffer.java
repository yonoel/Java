package buildCustomConcurrentTool;

public class BoundedBuffer<V> extends BaseBoundBuffer{
    public BoundedBuffer(int c) {
        super(c);
    }

    public synchronized void put(V v) throws InterruptedException{
        while (isFull())
            wait();
        doPut(v);
        notifyAll();
    }

    public synchronized void putCondition(V v) throws InterruptedException{
        while (isFull())
            wait();
        boolean wasEmpty = isEmpty();
        doPut(v);
        if (wasEmpty)
            notifyAll();
    }

    public synchronized V take()throws InterruptedException{
        while (isEmpty())
            wait();
        notifyAll();
        return (V) doTake();
    }
}
